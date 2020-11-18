
package Model;

import Controller.Clientes;
import Controller.Produtos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;


public class ProdutosDAO {
   
    EntityManagerFactory emf; // abetura de conexão com o banco de dados
    EntityManager em;         // realizar as operações

    public ProdutosDAO() {

        emf = Persistence.createEntityManagerFactory("sisbeb");// abrir a sessão no banco de dados
        em = emf.createEntityManager();                        // gerenciador

    }
    
        public boolean Salvar(Produtos pro, boolean validador) {

        try {

            em.getTransaction().begin(); // iniciar a conexão do banco
            em.merge(pro); // Salva o Objeto no banco
            em.getTransaction().commit();
            emf.close();

            JOptionPane.showMessageDialog(null, "PRODUTO SALVO COM SUCESSO!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR O PRODUTO");
            validador = false;
        }
        return validador;
    }
    
}
