package com.business;

import com.business.Excecoes.*;
import com.business.SubsistemaClientes.Cliente;
import com.business.SubsistemaClientes.ClienteLNFacade;
import com.business.SubsistemaClientes.IClienteLN;
import com.business.SubsistemaEquipamentos.Equipamento;
import com.business.SubsistemaEquipamentos.EquipamentoLNFacade;
import com.business.SubsistemaEquipamentos.IEquipamentoLN;
import com.business.SubsistemaFuncionarios.FuncionarioLNFacade;
import com.business.SubsistemaFuncionarios.IFuncionarioLN;
import com.business.SubsistemaOrcamentos.*;
import com.persistence.ISGCREEDL;
import com.persistence.SGCREEDLFacade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

public class CentroReparacoesLNFacade implements ICentroReparacoesLN, Serializable {
    IClienteLN clienteLN = new ClienteLNFacade();
    IEquipamentoLN equipamentoLN = new EquipamentoLNFacade();
    IFuncionarioLN funcionarioLN = new FuncionarioLNFacade();
    IOrcamentosLN orcamentosLN = new OrcamentosLNFacade();

    private static ISGCREEDL persistencia = new SGCREEDLFacade();

    //
    public CentroReparacoesLNFacade() {
        // Configuracao
        //try {
        //    //enviarEmailConfirmacao("");
        //} catch (OrcamentoInvalidoException e) {
//
  //      }
    }



    public boolean isTecnicoDisponivel() {
        return false;
    }

    //Orcamentos
    //returns codOrcamento
    public String registarOrcamentoProgramado(String nif, String codTecnico, String codPedidoOrcamento)
            throws ClienteInvalidoException, FuncionarioInvalidoException, PedidoOrcamentoInvalidoException {
        boolean nifValido = clienteLN.validarCliente(nif);
        boolean tecnicoValido = funcionarioLN.validarTecnico(codTecnico);
        boolean pedidoOrcamentoValido = orcamentosLN.validarPedidoOrcamento(codPedidoOrcamento);

        if (nifValido && tecnicoValido && pedidoOrcamentoValido) {
        String codEquipamento = orcamentosLN.getEquipamentoPedido(codPedidoOrcamento);
        orcamentosLN.removePedidoOrcamento(codPedidoOrcamento);
        return orcamentosLN.registarOrcamentoProgramado(nif, codTecnico, codEquipamento);
        }
        else {
                // Eu sei que ele vai fazer throw da primeira condição que der falsa
                // mas assim sempre dá para perceber qual o problema exato
            if (!nifValido) throw new ClienteInvalidoException();
            else if (!tecnicoValido) throw new FuncionarioInvalidoException();
            else throw new PedidoOrcamentoInvalidoException();
        }
    }
    @Override
    public void adicionarPasso(String descricao, double previsaoTempo, double custoPecas, String codOrcamento, String passo)
            throws OrcamentoInvalidoException {
        orcamentosLN.adicionarPasso(descricao, previsaoTempo, custoPecas, codOrcamento, passo);
    }
    @Override
    public void assinalarPasso(double duracao, double custoReal, String passo, String codOrcamento)
            throws OrcamentoInvalidoException, PassoInvalidoException {
        orcamentosLN.assinalarPasso(duracao,custoReal,passo,codOrcamento);
        if (orcamentosLN.verificarCustoUltrapassado(codOrcamento))
            enviarEmailCustoUltrapassado(codOrcamento);


    }
    public Passo getPasso(String codOrcamento, String passo) throws OrcamentoInvalidoException, PassoInvalidoException {
        return orcamentosLN.getPasso(codOrcamento, passo);
    }
    public List<Passo> getSubPassos(String codOrcamento, String passo)
            throws OrcamentoInvalidoException, PassoInvalidoException, SemSubPassosException {
        return orcamentosLN.getSubPassos(codOrcamento,passo);
    }
    @Override
    public void registarPedidoOrcamento(String nif, String nomeEquipamento, String codFunc)
            throws ClienteInvalidoException, FuncionarioInvalidoException {
        if (!validarCliente(nif)) throw new ClienteInvalidoException();
        if (!validarFuncBalcao(codFunc)) throw new FuncionarioInvalidoException();
        String codEquipamento = equipamentoLN.registarEquipamento(nif, nomeEquipamento);
        orcamentosLN.registarPedidoOrcamento(nif, codEquipamento, codFunc);
    }
    public List<String> getPedidosOrcamento(){
        return orcamentosLN.getPedidosOrcamento();
    }
    public PedidoOrcamento getPedidoOrcamento(String codPedido) throws PedidoOrcamentoInvalidoException {return orcamentosLN.getPedidoOrcamento(codPedido);}

    public List<String> getOrcamentosPorConfirmar() {
        return orcamentosLN.getOrcamentosPorConfirmar();
    }
    public List<String> getOrcamentosAndamento() {
        return orcamentosLN.getOrcamentosAndamento();
    }
    public List<String> getOrcamentosPorPagar() {
        return orcamentosLN.getOrcamentosPorPagar();
    }
    public List<String> getOrcamentosPagos() {
        return orcamentosLN.getOrcamentosPagos();
    }
    public List<String> getOrcamentosRecusados() {
        return orcamentosLN.getOrcamentosRecusados();
    }

    public Orcamento getOrcamento(String codOrcamento) throws OrcamentoInvalidoException {
        return orcamentosLN.getOrcamento(codOrcamento);
    }

    // Clientes
    @Override
    public boolean validarCliente(String nif) {
        return clienteLN.validarCliente(nif);
    }

    private boolean validarFuncBalcao(String codFunc) {
        try {
            funcionarioLN.getFuncBalcao(codFunc);
            return true;
        } catch (FuncionarioInvalidoException e) {
            return false;
        }
    }

    private boolean validarTecnico(String codTecnico) {
        // Não é a maneira mais bonita para fazer
        // Se restar tempo fazer refacor e implementar um funcionarioLN.validarTecnico();
        // A mesma coisa para o validarFunc
        try {
            funcionarioLN.getTecnico(codTecnico);
            return true;
        } catch (FuncionarioInvalidoException e) {
            return false;
        }
    }

    private boolean validarProduto(String produto) {
        return orcamentosLN.validaProduto(produto);
    }

    public void registarCliente(String nif, String nome, String email, String telemovel) throws ClienteInvalidoException{
        clienteLN.registarCliente(nif, nome, email, telemovel);
    }
    // returns List<codCliente>
    public List<String> getClientes() {
        return clienteLN.getClientes();
    }
    public Cliente getCliente(String codCliente) throws ClienteInvalidoException {
        return clienteLN.getCliente(codCliente);
    }

    // Equipamentos
    public List<String> getEquipamentosAndamento() {
        return equipamentoLN.getEquipamentosAndamento();
    }
    public List<String> getEquipamentosPago() {
        return equipamentoLN.getEquipamentosPago();
    }
    public List<String> getEquipamentosPorPagar() {
        return equipamentoLN.getEquipamentosPorPagar();
    }
    public List<String> getEquipamentosRecusado() {
        return equipamentoLN.getEquipamentosRecusado();
    }
    public List<String> getEquipamentosAbandonado() {
        return equipamentoLN.getEquipamentosAbandonado();
    }
    public Equipamento getEquipamento(String codEquipamento) throws EquipamentoInvalidoException {
        return equipamentoLN.getEquipamento(codEquipamento);
    }

    private static CentroReparacoesLNFacade single_instance = null;
    public static CentroReparacoesLNFacade getInstance() {
        if (single_instance == null) {
            try {
                single_instance = CentroReparacoesLNFacade.loadState();
            } catch (IOException e) {
                System.out.println("IOEXCEPTION");
                single_instance = new CentroReparacoesLNFacade();
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNOtFOUND");
            }
        }
        return single_instance;
    }

    @Override
    public void arquivarOrcamentoRecusado(String codOrcamento) throws OrcamentoInvalidoException, EquipamentoInvalidoException{
        orcamentosLN.arquivarOrcamentoRecusado(codOrcamento);
        equipamentoLN.registarEquipamentoRecusado(orcamentosLN.getCodEquipamentoOrcamento(codOrcamento));
    }


    public void registarPagamento(String codOrcamento) throws OrcamentoInvalidoException, EquipamentoInvalidoException{
        String codEquipamento = orcamentosLN.getCodEquipamentoOrcamento(codOrcamento);
        equipamentoLN.registarEquipamentoPago(codEquipamento);
        orcamentosLN.registarPagamento(codOrcamento);
    }


    public void registarOrcamentoConcluido(String codOrcamento) throws OrcamentoInvalidoException, EquipamentoInvalidoException {
        String codEquipamento = orcamentosLN.getCodEquipamentoOrcamento(codOrcamento);
        equipamentoLN.registarEquipamentoPorPagar(codEquipamento);
        orcamentosLN.registarOrcamentoConcluido(codOrcamento);
    }

    @Override
    public void registarPedidoOrcamentoExpresso(String nif, String nomeEquipamento, String codFunc)
            throws ClienteInvalidoException, FuncionarioInvalidoException, ProdutoInvalidoException, TecnicosIndisponiveisException {
        String codTecnico = getTecnicoDisponivel();
        if (!validarCliente(nif)) throw new ClienteInvalidoException();
        if (!validarFuncBalcao(codFunc) || !validarTecnico(codTecnico)) throw new FuncionarioInvalidoException();
        // nomeEquipamento tem que ser igual ao produto do serviço expresso
        if (!validarProduto(nomeEquipamento)) throw new ProdutoInvalidoException();
        String codEquipamento = equipamentoLN.registarEquipamento(nif, nomeEquipamento);
        orcamentosLN.registarPedidoOrcamentoExpresso(nif, codEquipamento, codFunc, codTecnico);
    }

    @Override
    public String registarOrcamentoExpresso(String nif, String codPedidoOrcamento)
            throws PedidoOrcamentoInvalidoException, ProdutoInvalidoException, EquipamentoInvalidoException {
        String codEquipamento = orcamentosLN.getEquipamentoPedido(codPedidoOrcamento);
        Equipamento e = equipamentoLN.getEquipamento(codEquipamento);
        String produto = e.getNomeEquipamento();
        return orcamentosLN.registarOrcamentoExpresso(nif, produto, codPedidoOrcamento);
    }

    public void registarOrcamentoAndamento(String codOrcamento) throws OrcamentoInvalidoException {
        orcamentosLN.registarOrcamentoAndamento(codOrcamento);
    }

    @Override
    public void enviarEmailConfirmacao(String codOrcamento) throws OrcamentoInvalidoException {
        String text = "";
        Orcamento o = orcamentosLN.getOrcamento(codOrcamento);
        String nomeCliente = "";
        try {
            nomeCliente = clienteLN.getCliente(o.getCodCliente()).getNome();
        } catch (ClienteInvalidoException e) {
            e.printStackTrace();
        }
        text += "Olá " + nomeCliente + "! \nO grupo 11 lhe envia este e-mail com a proposta de orçamento para o seu pedido.\n";
        text += "\nINFORMAÇÕES: \n\n";
        if (o instanceof OrcamentoProgramado){
            OrcamentoProgramado op = (OrcamentoProgramado) o;
            text += "Código do Orçamento: " + op.getCodOrcamento() + "\n";
            text += "Preço: "  + op.getPrecoTotal() + " euros\n";
            text += "Prazo: "  + op.getPrazo() + " horas";
        }

        text += "\n\nO Código do Orçamento deve ser utilizado no momento do registo do pagamento.\n\n";
        text += "O grupo 11 aguarda pela sua resposta!";
        Cliente c = null;
        try {
            c = clienteLN.getCliente(o.getCodCliente());
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.ssl.enable", "true");
            final String from = "trabalhodss2021@gmail.com";

            javax.mail.Session session = javax.mail.Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, "dsstrabalho2021");
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(c.getEmail()));
                message.setSubject("Proposta de Orçamento - grupo 11");
                message.setText(text);
                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } catch (ClienteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enviarEmailConclusao(String codOrcamento) throws OrcamentoInvalidoException {
        String text = "";
        Orcamento o = orcamentosLN.getOrcamento(codOrcamento);
        String nomeCliente = "";
        try {
            nomeCliente = clienteLN.getCliente(o.getCodCliente()).getNome();
        } catch (ClienteInvalidoException e) {
            e.printStackTrace();
        }
        text += "Olá " + nomeCliente + "! \nO grupo 11 lhe envia este e-mail para lhe informar que a reparação do orçamento " + o.getCodOrcamento() + " foi concluída.\n";
        text += "Já pode vir levantar o equipamento e realizar o seu pagamento.";
        Cliente c = null;
        try {
            c = clienteLN.getCliente(o.getCodCliente());
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.ssl.enable", "true");
            final String from = "trabalhodss2021@gmail.com";

            javax.mail.Session session = javax.mail.Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, "dsstrabalho2021");
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(c.getEmail()));
                message.setSubject("Orçamento concluído - grupo 11");
                message.setText(text);
                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } catch (ClienteInvalidoException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailCustoUltrapassado(String codOrcamento) throws OrcamentoInvalidoException {
        String text = "";
        Orcamento o = orcamentosLN.getOrcamento(codOrcamento);
        String nomeCliente = "";
        try {
            nomeCliente = clienteLN.getCliente(o.getCodCliente()).getNome();
        } catch (ClienteInvalidoException e) {
            e.printStackTrace();
        }

        if (o instanceof OrcamentoProgramado) {
            OrcamentoProgramado op = (OrcamentoProgramado) o;
            text += "Olá " + nomeCliente + "! \nO grupo 11 lhe envia este e-mail para lhe informar que a reparação do orçamento" + o.getCodOrcamento() + "excedeu 120% do valor estimado.\n";
            text += "\nO preco confirmado foi de " + op.getPrecoTotal() +
                    " euros, no entanto, o preço da reparação atingiu o valor de " + op.getPrecoRealTotal() +
                    " euros. Se pretender recusar responda a este email.";
        }
        Cliente c;
        try {
            c = clienteLN.getCliente(o.getCodCliente());
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.ssl.enable", "true");
            final String from = "trabalhodss2021@gmail.com";

            javax.mail.Session session = javax.mail.Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, "dsstrabalho2021");
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(c.getEmail()));
                message.setSubject("Custo Ultrapassado 120% - grupo 11");
                message.setText(text);

                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } catch (ClienteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean orcamentoConcluido(String codOrcamento) throws OrcamentoInvalidoException {
        return orcamentosLN.orcamentoConcluido(codOrcamento);
    }

    @Override
    public void registarOrcamentoExpressoConcluido(String codOrcamento) throws OrcamentoInvalidoException, EquipamentoInvalidoException {
        registarOrcamentoConcluido(codOrcamento);
        enviarSMSConcluido(codOrcamento);
    }

    public void enviarSMSConcluido(String codOrcamento) {
        //System.out.println("Reparação feita!");
    }

    private String getTecnicoDisponivel() throws TecnicosIndisponiveisException {
        List<String> tecnicos = funcionarioLN.getTecnicos();
        if (tecnicos.size() == 0) throw new TecnicosIndisponiveisException();
        String tecnico = tecnicos.get(0);
        return tecnico;
    }

    public static CentroReparacoesLNFacade loadState() throws IOException, ClassNotFoundException {
       return ISGCREEDL.loadState();
    }

    public void saveState() throws IOException {
        persistencia.saveState(this);
    }
}

