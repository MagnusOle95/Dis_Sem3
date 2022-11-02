import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Opgave19_2_Optimistisk_Låsning {

    static BufferedReader inline;


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            System.out.println("Program startet");
            Connection minConnection;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;" +
                    "databaseName=Dis_Sem3_Dag14;user=sa;password=Abe123;");

            Statement stmt = minConnection.createStatement();
            stmt.execute("set transaction isolation level serializable");

            inline = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Indtast Kontonr til den konto du ønsker at rette saldoen på");
            String kontonrIn = inline.readLine();

            ResultSet KontoRes=stmt.executeQuery("select * from konto where kontonr ='" + kontonrIn + "'");
            if (KontoRes.next()) {
                int kontonr = KontoRes.getInt(1);
                int Kontosaldo = KontoRes.getInt(2);
                int kundenr = KontoRes.getInt(3);
                System.out.println("Konto Nr: " + kontonr + " Saldo: " + Kontosaldo + " kunde Nr: " + kundenr);

                //Mellemrum
                System.out.println("indtast den saldo kontoen skal have");
                String nySaldo = inline.readLine();

                stmt.execute("begin tran");
                ResultSet KontoResTjek=stmt.executeQuery("select * from konto where kontonr ='" + kontonrIn + "'");

                if (KontoResTjek.next()) {

                    if (KontoResTjek.getInt(1) == kontonr && KontoResTjek.getInt(2) == Kontosaldo && KontoResTjek.getInt(3) == kundenr) {
                        stmt.execute("update konto set saldo = '" + nySaldo + "' where kontonr = '" + kontonrIn + "'");
                        stmt.execute("commit tran");
                        System.out.println("Opdatering af konto fuldført");

                    } else {
                        System.out.println("Øv kunne ikke lade sig gøre");
                        stmt.execute("rollback tran");
                    }
                }
            }
            else{
                stmt.execute("rollback tran");
                System.out.println("konto eksistere ikke");
            }

            if (stmt != null)
                stmt.close();
            if (minConnection != null)
                minConnection.close();
        }
        catch (Exception e) {
            System.out.print("fejl:  "+e.getMessage());
        }
    }

}
