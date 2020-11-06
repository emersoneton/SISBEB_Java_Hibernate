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

    public void Salvar(Clientes cli) {

        try {

            em.getTransaction().begin(); // iniciar a conexão do banco
            em.merge(cli); // Salva o Objeto no banco
            em.getTransaction().commit();
            emf.close();

            JOptionPane.showMessageDialog(null, "CLIENTE SALVO COM SUCESSO!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR O CLIENTE");
        }
    }

    public void Deletar(Clientes cli) {
        em.getTransaction().begin(); // iniciar a conexão do banco
        Query delete = em.createNativeQuery("delete from clientes where nome = '" + cli.getNome() + "'");
        delete.executeUpdate();
        em.getTransaction().commit();
        emf.close();
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

    public List<Clientes> Buscar(Clientes cli) {
        em.getTransaction().begin(); // iniciar a conexão do banco

        Query buscar = em.createQuery("SELECT clientes from Clientes clientes where clientes.codigo = '"+cli.getCodigo()+"' Order by nome");
        List<Clientes> lista = buscar.getResultList();

        em.getTransaction().commit();
        emf.close();

        return lista;
    }

    public void Update(Clientes cli) {
        em.getTransaction().begin(); // iniciar a conexão do banco
        Query update = em.createNativeQuery("update clientes set nome = '" + cli.getNome() + "' where id = 4");
        update.executeUpdate();
        em.getTransaction().commit();
        emf.close();
    }
}
