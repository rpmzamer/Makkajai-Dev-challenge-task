import controller.SalesTaxController;
import service.*;
import tax.*;
import ui.ConsoleUI;
import util.InputParser;

import java.util.List;

public class SalesTaxApp {
    public static void main(String[] args) {
        TaxCalculator taxCalculator = new TaxCalculator(List.of(
                new BasicSalesTax(),
                new ImportDutyTax()
        ));

        ReceiptService receiptService = new ReceiptService(taxCalculator);
        ConsoleUI ui = new ConsoleUI();
        InputParser parser = new InputParser();

        SalesTaxController controller = new SalesTaxController(ui, receiptService, parser);
        controller.run();
    }
}
