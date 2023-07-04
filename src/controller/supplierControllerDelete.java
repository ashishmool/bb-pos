import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class supplierControllerDelete {
    public static boolean deleteSupplier(supplierModel supplier){
    try{
        Connection connection = myConnection.myDatabase();
        String sql = "DELETE FROM supplier WHERE s_id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setObject(1, supplier.getSupplier_id());
        int result = pst.executeUpdate();
        return (result>0);
    }
    catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
        return false;
}

    
}
