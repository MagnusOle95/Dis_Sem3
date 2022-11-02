import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App_Opgave16_17_18 {

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
            System.out.println("Indtast Konto du ønsker at sende fra");
            String fraKonto = inline.readLine();
            System.out.println("Indtast konto du ønsker at sende til");
            String tilKonto = inline.readLine();
            System.out.println("Skriv beløbet, du vil overføre i kr");
            int beløb = Integer.parseInt(inline.readLine());

            //xlock virker.
            //updlock virker.
            //no lock virker ikke.

            stmt.execute("begin tran");
            ResultSet fraKontoRes=stmt.executeQuery("select * from konto (xlock) where kontonr ='" + fraKonto + "'");
            if (fraKontoRes.next()) {
                int fraKontosaldo = fraKontoRes.getInt(2);
                if (fraKontosaldo >= beløb){
                    ResultSet tilKontoRes=stmt.executeQuery("select * from konto (xlock) where kontonr ='" + tilKonto + "'");
                    if (tilKontoRes.next()){
                        int tilKontosaldo = tilKontoRes.getInt(2);
                        int nyFrakontoSaldo = fraKontosaldo - beløb;
                        int nyTilkontoSaldo = tilKontosaldo + beløb;
                        String pause = inline.readLine();
                        stmt.execute("update konto set saldo = '" + nyFrakontoSaldo + "' where kontonr = '" + fraKonto + "'");
                        stmt.execute("update konto set saldo = '" + nyTilkontoSaldo + "' where kontonr = '" + tilKonto + "'");

                        stmt.execute("commit tran");
                        System.out.println("Transaktion komplet");
                    }
                    else{
                        stmt.execute("rollback tran");
                        System.out.println("Til konto eksistere ikke");
                    }
                }
                else{
                    stmt.execute("rollback tran");
                    System.out.println("Saldoen undersøtter ikke det beløb der ønskes overført");
                }
            }
            else{
                stmt.execute("rollback tran");
                System.out.println("Fra konto eksistere ikke");
            }

            /*
            ResultSet res=stmt.executeQuery("select * from konto");
            while (res.next()) {
                System.out.println("Konto " + res.getInt(1) + " har saldo " + res.getInt(2) + " og ejer " + res.getString(3) );
            }
             */

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
