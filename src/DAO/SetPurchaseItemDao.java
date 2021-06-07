package DAO;

public class SetPurchaseItemDao {
    public void insert(){
        String str = "INSERT INTO `pos`.`purchase_item`\n" +
                "(`PURCHASE_VOUCHER_ID`,\n" +
                "`PUR_ITEM_ID`,\n" +
                "`PID`,\n" +
                "`QUANTITY`,\n" +
                "`GROSS`,\n" +
                "`DISCOUNT`,\n" +
                "`TAXABLE_VALUE`,\n" +
                "`IGST`,\n" +
                "`CGST`,\n" +
                "`SGST`,\n" +
                "`VISIBILITY`,\n" +
                "`DATE_UPDATED`,\n" +
                "`DATE_CREATED`,\n" +
                "`Coolie`,\n" +
                "`Freight`)\n" +
                "VALUES\n" +
                "(<{PURCHASE_VOUCHER_ID: }>,\n" +
                "<{PUR_ITEM_ID: }>,\n" +
                "<{PID: }>,\n" +
                "<{QUANTITY: }>,\n" +
                "<{GROSS: }>,\n" +
                "<{DISCOUNT: 0}>,\n" +
                "<{TAXABLE_VALUE: }>,\n" +
                "<{IGST: 0}>,\n" +
                "<{CGST: 0}>,\n" +
                "<{SGST: 0}>,\n" +
                "<{VISIBILITY: 1}>,\n" +
                "<{DATE_UPDATED: }>,\n" +
                "<{DATE_CREATED: CURRENT_TIMESTAMP}>,\n" +
                "<{Coolie: 0.00}>,\n" +
                "<{Freight: 0.00}>);\n";
    }
}
