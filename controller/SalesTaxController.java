package controller;

import model.Item;
import service.ReceiptService;
import util.InputParser;
import util.InvalidItemFormatException;
import ui.ConsoleUI;

public class SalesTaxController {
    private final ConsoleUI ui;
    private final ReceiptService receiptService;
    private final InputParser inputParser;

    public SalesTaxController(ConsoleUI ui, ReceiptService receiptService, InputParser inputParser) {
        this.ui = ui;
        this.receiptService = receiptService;
        this.inputParser = inputParser;
    }

    public void run() {
        ui.showMessage("Enter items (e.g. '1 imported box of chocolates at 10.00'):");
        ui.showMessage("Type 'done' to finish.");

        while (true) {
            String input = ui.prompt("> ");
            if ("done".equalsIgnoreCase(input.trim())) break;

            try {
                Item item = inputParser.parse(input);
                receiptService.addItem(item);
            } catch (InvalidItemFormatException e) {
                ui.showMessage("Error: " + e.getMessage());
            }
        }

        ui.showMessage("\nYour Receipt:");
        receiptService.printReceipt();
    }
}
