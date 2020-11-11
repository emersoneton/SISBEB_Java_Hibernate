package Model;

import Controller.Clientes;
import Controller.Estados;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class ClientesDAO {

    EntityManagerFactory emf; // abetura de conexão com o banco de dados
    EntityManager em;         // realizar as operações

    public ClientesDAO() {

        emf = Persistence.createEntityManagerFactory("sisbeb");// abrir a sessão no banco de dados
        em = emf.createEntityManager();                        // gerenciador

    }

    public boolean Salvar(Clientes cli, boolean validador) {

        try {

            em.getTransaction().begin(); // iniciar a conexão do banco
            em.merge(cli); // Salva o Objeto no banco
            em.getTransaction().commit();
            emf.close();

            JOptionPane.showMessageDialog(null, "CLIENTE SALVO COM SUCESSO!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR O CLIENTE");
            validador = false;
        }
        return validador;
    }

    public boolean Deletar(Clientes cli,boolean validador) {
        try {
            
        em.getTransaction().begin(); // iniciar a conexão do banco
        Query delete = em.createNativeQuery("delete from Clientes where codigo = '"+cli.getCodigo()+"'");
        delete.executeUpdate();
        em.getTransaction().commit();
        emf.close();
        
        JOptionPane.showMessageDialog(null, "CLIENTE APAGADO COM SUCESSO!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO DELETAR O CLIENTE");
            validador = false;
        }        
        return validador;
    }

    public List<Estados> BuscarEstado(Estados estado) {

        try { // VERIFICA CONEXÃO COM O BANCO DE DADOS
            
            em.getTransaction().begin(); // iniciar a conexão do banco
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO ACESSAR O BANCO DE DADOS \n\n"
                    + "ABRA O GERENCIADOR DO BANCO E TENTE NOVAMENTE");
        }

        Query buscar = em.createQuery("SELECT estado from Estados estado Order by nome");
        List<Estados> lista = buscar.getResultList();

        em.getTransaction().commit();
        emf.close();

        return lista;
    }
    
    public List<Clientes> BuscarProximoCodigo(){
        em.getTransaction().begin();
        
        Query buscar = em.createQuery("SELECT clientes from Clientes clientes");
        List<Clientes> lista = buscar.getResultList();

        em.getTransaction().commit();
        emf.close();

        return lista; 
    }

    public List<Clientes> Buscar(Clientes cli) {
        em.getTransaction().begin(); // iniciar a conexão do banco

        Query buscar = em.createQuery("SELECT clientes from Clientes clientes where clientes.codigo = '"+cli.getCodigo()+"' Order by nome");
        List<Clientes> lista = buscar.getResultList();

        em.getTransaction().commit();
        emf.close();

        return lista;
    }

    public boolean Alterar(Clientes cli, boolean validador) {
        
        try {
           
        em.getTransaction().begin(); // iniciar a conexão do banco
        Query update = em.createNativeQuery("update Clientes set nome = :nome, cep = :cep, endereco = :endereco,"
                + "numero = :numero, cidade = :cidade, estado = :estado, bairro = :bairro, telefone = :telefone,"
                + "situacao = :situacao where codigo = :codigo");
        update.setParameter("nome", cli.getNome());
        update.setParameter("cep", cli.getCep());
        update.setParameter("endereco", cli.getEndereco());
        update.setParameter("numero", cli.getNumero());
        update.setParameter("cidade", cli.getCidade());
        update.setParameter("estado", cli.getEstado());
        update.setParameter("bairro", cli.getBairro());
        update.setParameter("telefone", cli.getTelefone());
        update.setParameter("situacao", cli.getSituacao());
        update.setParameter("codigo", cli.getCodigo());
        update.executeUpdate();
        em.getTransaction().commit();
        emf.close();
        
        JOptionPane.showMessageDialog(null, "CADASTRO ALTERADO COM SUCESSO!");
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR OS DADOS DO CLIENTE");
            validador = false;
        }
        return validador;
    }
    
    
    public List<Clientes> BuscarClientes(){
        em.getTransaction().begin();
        
        Query buscar = em.createQuery("SELECT clientes from Clientes clientes Order by nome");
        List<Clientes> lista = buscar.getResultList();

        em.getTransaction().commit();
        emf.close();

        return lista; 
    }
    
}
