package br.com.restful.factory;
 
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
 
   //Nome do usu�rio do mysql
   private static final String USERNAME = "lu2cas01";
 
   //Senha do mysql
   private static final String PASSWORD = "guaranait123";
 
   //Dados de caminho, porta e nome da base de dados que ir� ser feita a conex�o
   private static final String DATABASE_URL = "jdbc:mysql://mysql.lu2cas.com.br";
   /**
   * Cria uma conex�o com o banco de dados MySQL utilizando o nome de usu�rio e senha fornecidos
   * @param username
   * @param senha
   * @return uma conex�o com o banco de dados
   * @throws Exception
   */
   public static Connection criarConexao() throws Exception{
      Class.forName("com.mysql.jdbc.Driver"); //Faz com que a classe seja carregada pela JVM
 
      //Cria a conex�o com o banco de dados
      Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
 
      return connection;
   }
   public static void main(String[] args) throws Exception{
 
      //Recupera uma conex�o com o banco de dados
      Connection con = criarConexao();
 
      //Testa se a conex�o � nula
      if(con != null){
         System.out.println("Conex�o obtida com sucesso!" + con);
         con.close();
      }
 
   }
}