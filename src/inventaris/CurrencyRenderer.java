/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventaris;

/**
 *
 * @author aliff_qdx6
 */
import javax.swing.table.DefaultTableCellRenderer;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyRenderer extends DefaultTableCellRenderer {
    private final NumberFormat currencyFormat;

    public CurrencyRenderer() {
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    }

    @Override
    protected void setValue(Object value) {
        if (value instanceof Number) {
            setText(currencyFormat.format(value));
        } else {
            setText(value.toString());
        }
    }
}

