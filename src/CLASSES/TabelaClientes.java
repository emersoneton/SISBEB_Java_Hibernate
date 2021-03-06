package CLASSES;

import Controller.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaClientes extends AbstractTableModel {

    private List<Clientes> dados = new ArrayList<>();      // Arrai do dados da classe de Cadastro de Serviços que vão ser informados na tabela
    private String[] colunas = {"Código", "Nome", "Endereço", "Numero", "Telefone"}; // Criação das Colunas no Jtable

    @Override
    public String getColumnName(int culumn) {
        return colunas[culumn];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getCodigo();  // Insere os dados na Tabela
            case 1:
                return dados.get(linha).getNome();  // Insere os dados na Tabela
            case 2:
                return dados.get(linha).getEndereco();      // Insere os dados na Tabela
            case 3:
                return dados.get(linha).getNumero(); // Insere os dados na Tabela
            case 4:
                return dados.get(linha).getTelefone(); // Insere os dados na Tabela    
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
         switch(coluna){
           case 0:
                dados.get(linha).setCodigo(Integer.parseInt((String) valor));  // Insere os dados na Tabela
                break;  
           case 1:
                dados.get(linha).setNome((String) valor);  // Insere os dados na Tabela
                break;
           case 2:
                dados.get(linha).setEndereco((String) valor);      // Insere os dados na Tabela
                break;
           case 3:
                dados.get(linha).setNumero(Integer.parseInt((String) valor)); // Insere os dados na Tabela
                break;
            case 4:
                dados.get(linha).setTelefone((String) valor); // Insere os dados na Tabela
                break;    
       }
         this.fireTableRowsUpdated(linha, linha);
    }
    
    
    
    public void addRow(Clientes cli){
        this.dados.add(cli);            // Insere os dados na Jtable
        this.fireTableDataChanged(); // Atualiza a Jtable
    }
    
    public void removeRow(int linha){
        this.dados.remove(linha);                 // Remove os dados na Jtable
        this.fireTableRowsDeleted(linha, linha);  // Atualiza a Jtable
    }

}
