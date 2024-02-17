package praktikum2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.formdev.flatlaf.FlatLightLaf;

public class RechenMaxUI extends JComponent {

  // handle Backspace
  protected class BackspaceAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public BackspaceAction() {
      super("⌫", KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      final String resultText = getResultText();
      if (getResultText() != "Ungültige Eingabe") {
        if (!"0".equals(resultText) && !resultText.isEmpty()) {
          setResultText(resultText.substring(0, resultText.length() - 1));
          if ("-".equals(getResultText())) {
            setResultText(getResultText() + "0");
          }
        } else {
          setResultText('0');
        }
        if (getResultText() == "") {
          setResultText('0');
        }
      }
      final int index = getResultText().indexOf(',');
      if (index != -1) {
        final String result = getResultText().substring(0, index).replace(".", "");
        final String result2 = getResultText().substring(index, getResultText().length());
        final DecimalFormat decimalFormat = new DecimalFormat("#,###");
        final String formattedNumber = decimalFormat.format(Long.parseLong(result));
        final String text2 = formattedNumber + result2;
        setResultText(text2);
      } else {
        final String result = getResultText().replace(".", "");
        final DecimalFormat decimalFormat = new DecimalFormat("#,###");
        final String formattedNumber = decimalFormat.format(Long.parseLong(result));
        setResultText(formattedNumber);
      }
      // Result-Text Size
      final int len = getResultText().replace(",", "").replace(".", "").length();
      if (len >= 12) {
        resultlabel.setFont(new Font("Serif", Font.PLAIN, 40));
      } else {
        resultlabel.setFont(new Font("Serif", Font.PLAIN, 45));
      }
      if (len >= 14) {
        resultlabel.setFont(new Font("Serif", Font.PLAIN, 35));
      }
    }
  }

  // Calculate
  protected class CalculateAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public CalculateAction() {
      super("=", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      // Replace special characters for proper calculation
      String calcText = getCalculateText().replace("*", "×").replace("/", "÷");
      // Check for valid input before performing calculations
      if (!isInvalidInput(getResultText()) && !isInvalidInput(getCalculateText())) {
        // Handle calculation based on the rotate operator flag
        if (getRotateOperator()) {
          if (!calcText.contains("=")) {
            // Handle calculation when equals sign is not present
            setLastNumber(getResultText());
            setCalculateText(calcText + " =");
            setResultText(RechenMaxCalculator.calculate(getCalculateText().replace("×", "*").replace("÷", "/")));
          } else {
            // Handle calculation when equals sign is present
            if (!getCalculateText().replace("=", "").replace(" ", "").matches("^(sin|cos|tan)\\(.*\\)$")) {
              if (!getLastOp().isEmpty() && !getLastOp().equals("√")) {
                setCalculateText(getResultText() + " " + getLastOp() + " " + getLastNumber() + " =");
              } else {
                setCalculateText(getResultText() + " =");
              }
              setResultText(
                  RechenMaxCalculator.calculate(getResultText() + " " + getLastOp().replace("×", "*").replace("÷", "/") + " " + getLastNumber()));
            } else {
              setResultText(RechenMaxCalculator.calculate(getCalculateText()));
            }
          }
        } else {
          if (!calcText.contains("=")) {
            // Handle calculation when equals sign is not present
            setLastNumber(getResultText());
            calculatelabel.setText(calcText + " " + getResultText() + " =");
            resultlabel.setText(RechenMaxCalculator.calculate(getCalculateText().replace("×", "*").replace("÷", "/")));
          } else {
            // Handle calculation when equals sign is present
            if (!getCalculateText().replace("=", "").replace(" ", "").matches("^(sin|cos|tan)\\(.*\\)$")) {
              if (!getLastOp().isEmpty()) {
                calculatelabel.setText(getResultText() + " " + getLastOp() + " " + getLastNumber() + " =");
              } else {
                calculatelabel.setText(getResultText() + " =");
              }
              resultlabel.setText(
                  RechenMaxCalculator.calculate(getResultText() + " " + getLastOp().replace("×", "*").replace("÷", "/") + " " + getLastNumber()));
            } else {
              setResultText(RechenMaxCalculator.calculate(getCalculateText()));
            }
          }
        }
        // Replace special characters back for displaying
        setCalculateText(getCalculateText().replace("*", "×").replace("/", "÷"));
      } else {
        // Handle invalid input by resetting result text and calculate text
        setResultText("0");
        setCalculateText("");
      }
      // Reset rotate operator flag, format result text, adjust text size, and scroll to bottom if necessary
      setRotateOperator(false);
      setRemoveValue(true);
      formatResultTextAfterType();
      adjustTextSize();
    }
  }

  /**
   * Formats the result text after a mathematical operation has been performed. This method handles various formats, including scientific notation and
   * decimal formatting.
   */
  public void formatResultTextAfterType() {
    // Get the result text
    String text = getResultText();
    // Check if result text is not null
    if (text != null) {
      // Check if the number is negative
      boolean isNegative = text.startsWith("-");
      if (isNegative) {
        // If negative, remove the negative sign for further processing
        text = text.substring(1);
      }
      // Check for scientific notation
      if (text.toLowerCase().matches(".*[eE].*")) {
        try {
          // Convert scientific notation to BigDecimal with increased precision
          BigDecimal bigDecimalResult = new BigDecimal(text.replace(".", "").replace(",", "."), MathContext.DECIMAL128);
          String formattedNumber = bigDecimalResult.toPlainString();
          formattedNumber = formattedNumber.replace(".", ",");
          // Extract exponent part and shift decimal point accordingly
          String[] parts = formattedNumber.split("[eE]");
          if (parts.length == 2) {
            int exponent = Integer.parseInt(parts[1]);
            String[] numberParts = parts[0].split(",");
            if (exponent < 0) {
              // Shift decimal point to the left, allowing up to 9 positions
              int shiftIndex = Math.min(numberParts[0].length() + exponent, 9);
              formattedNumber = numberParts[0].substring(0, shiftIndex) + "," + numberParts[0].substring(shiftIndex) + numberParts[1] + "e"
                  + exponent;
            } else {
              // Shift decimal point to the right
              int shiftIndex = Math.min(numberParts[0].length() + exponent, numberParts[0].length());
              formattedNumber = numberParts[0].substring(0, shiftIndex) + "," + numberParts[0].substring(shiftIndex) + numberParts[1];
            }
          }
          // Add negative sign if necessary and set the result text
          if (isNegative) {
            formattedNumber = "-" + formattedNumber;
          }
          setResultText(formattedNumber.replace("E", "e"));
          // Adjust text size and recursively call the method
          adjustTextSize();
          formatResultTextAfterType();
          /**
           * // Save calculation to history in a separate thread String finalText = text; new Thread(() -> runOnUiThread(() -> { final String value =
           * dataManager.readFromJSON("historyTextViewNumber", context1); if (value == null) { dataManager.saveToJSON("historyTextViewNumber", "0",
           * context1); } else { final int old_value = Integer.parseInt(dataManager.readFromJSON("historyTextViewNumber", context1));
           *
           * dataManager.saveToJSON("historyTextViewNumber", Integer.toString(old_value + 1), context1);
           * dataManager.saveToJSON(String.valueOf(old_value + 1), finalText.replace("E", "e") + " = " + getResultText(), context1); }
           *
           * // Log historyTextViewNumber value for debugging Log.i("Calculate", "historyTextViewNumber: " +
           * dataManager.readFromJSON("historyTextViewNumber", context1)); })).start(); return;
           */
        } catch (NumberFormatException e) {
          // Handle invalid number format in scientific notation
          System.out.println("Invalid number format: " + text);
        }
      }
      // Handle non-scientific notation
      int index = text.indexOf(',');
      String result;
      String result2;
      if (index != -1) {
        // Split the text into integral and fractional parts
        result = text.substring(0, index).replace(".", "");
        result2 = text.substring(index);
      } else {
        result = text.replace(".", "");
        result2 = "";
      }
      // Check for invalid input
      if (!isInvalidInput(getResultText())) {
        // Format the integral part using DecimalFormat
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        try {
          BigDecimal bigDecimalResult1 = new BigDecimal(result, MathContext.DECIMAL128);
          String formattedNumber1 = decimalFormat.format(bigDecimalResult1);
          String formattedNumber2 = result2;
          // Set the result text with formatted numbers
          setResultText((isNegative ? "-" : "") + formattedNumber1 + formattedNumber2);
        } catch (NumberFormatException e) {
          // Handle invalid number format in the integral part
          System.out.println("Invalid number format: " + result);
        }
      } else if (getIsNotation()) {
        // Reset scientific notation flag if needed
        setIsNotation(false);
      }
      // Adjust text size
      adjustTextSize();
    }
  }

  /**
   * This method adjusts the text size of the result label based on its length. If the result text is not "Nur reelle Zahlen" and its length is 12 or
   * more, the text size is reduced to fit the label. If the result text is "Ungültige Eingabe" or "Nur reelle Zahlen", the text size is set to a
   * specific value.
   */
  public void adjustTextSize() {
    if (getResultText() != null) {
      int len = getResultText().replace(",", "").replace(".", "").replace("-", "").length();
      if (!getResultText().equals("Nur reelle Zahlen")) {
        if (!getResultText().equals("Ungültige Eingabe")) {
          if (len >= 12) {
            resultlabel.setFont(new Font("Serif", Font.PLAIN, 45));
            if (len >= 14) {
              resultlabel.setFont(new Font("Serif", Font.PLAIN, 40));
              if (len >= 15) {
                resultlabel.setFont(new Font("Serif", Font.PLAIN, 35));
                if (len >= 17) {
                  resultlabel.setFont(new Font("Serif", Font.PLAIN, 31));
                }
              }
            }
          } else {
            resultlabel.setFont(new Font("Serif", Font.PLAIN, 55));
          }
        } else {
          resultlabel.setFont(new Font("Serif", Font.PLAIN, 45));
        }
      } else {
        resultlabel.setFont(new Font("Serif", Font.PLAIN, 50));
      }
    }
  }

  // handle comma
  protected class CommaAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public CommaAction() {
      super(",", KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, 0));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      if (!getResultText().contains(",")) {
        setResultText(getResultText() + ",");
      }
    }
  }

  protected class CopyToClipboardAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public CopyToClipboardAction(final String op, final int key) {
      this(op, KeyStroke.getKeyStroke(key, 0));
    }

    public CopyToClipboardAction(final String op, final KeyStroke... keyStrokes) {
      super(op, keyStrokes);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(getResultText()), null);
    }
  }

  // handle CE
  protected class EmptyAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public EmptyAction() {
      super("CE", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      setResultText("0");
      resultlabel.setFont(new Font("Serif", Font.PLAIN, 45));
    }
  }

  // handle C
  protected class EmptyAllAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public EmptyAllAction() {
      super("C", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      setResultText("0");
      setCalculateText("");
      resultlabel.setFont(new Font("Serif", Font.PLAIN, 45));
    }
  }

  protected class EmptyClipboard extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public EmptyClipboard(final String op, final int key) {
      this(op, KeyStroke.getKeyStroke(key, 0));
    }

    public EmptyClipboard(final String op, final KeyStroke... keyStrokes) {
      super(op, keyStrokes);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
    }
  }

  /**
   * Appends or sets the text "sin(" to the calculation input. Scrolls to the bottom of the scroll view if it exists.
   */
  protected class Sine extends MyOpAction {

    public Sine() {
      super("sin(x)");
      // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(final ActionEvent e) {
      // Check if calculate text is empty and set or add
      if (getCalculateText().isEmpty()) {
        setCalculateText("sin(");
      } else {
        addCalculateText("sin(");
      }
    }
  }

  /**
   * Appends or sets the text "cos(" to the calculation input. Scrolls to the bottom of the scroll view if it exists.
   */
  protected class Cosine extends MyOpAction {

    public Cosine() {
      super("cos(x)");
      // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(final ActionEvent e) {
      // Check if calculate text is empty and set or add
      if (getCalculateText().isEmpty()) {
        setCalculateText("cos(");
      } else {
        addCalculateText("cos(");
      }
    }
  }

  /**
   * Appends or sets the text "tan(" to the calculation input. Scrolls to the bottom of the scroll view if it exists.
   */
  protected class Tangent extends MyOpAction {

    public Tangent() {
      super("tan(x)");
      // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(final ActionEvent e) {
      // Check if calculate text is empty and set or add
      if (getCalculateText().isEmpty()) {
        setCalculateText("tan(");
      } else {
        addCalculateText("tan(");
      }
    }
  }

  /**
   * Appends or sets the text "π" to the calculation input and sets the rotate operator flag to true.
   */
  protected class Pi extends MyOpAction {

    public Pi() {
      super("π");
      // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(final ActionEvent e) {
      if (getCalculateText().isEmpty()) {
        setCalculateText("π");
      } else {
        addCalculateText("π");
      }
      setRotateOperator(true);
    }
  }

  protected class parenthesisOn extends MyOpAction {

    public parenthesisOn() {
      super("(");
      // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(final ActionEvent e) {
      if (getCalculateText().isEmpty()) {
        setCalculateText("(");
      } else {
        addCalculateText("(");
      }
    }
  }

  protected class parenthesisOff extends MyOpAction {

    public parenthesisOff() {
      super(")");
      // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(final ActionEvent e) {
      Pattern pattern = Pattern.compile("√\\(\\d+\\)$");
      Matcher matcher = pattern.matcher(getCalculateText());
      if (!getCalculateText().isEmpty()) {
        if (matcher.find()) {
          addCalculateText(")");
        } else {
          if (!getRotateOperator()) {
            addCalculateText(" " + getResultText() + " )");
          } else {
            addCalculateText(")");
          }
        }
        setRotateOperator(true);
      }
    }
  }

  protected class factorial extends MyOpAction {

    public factorial() {
      super("x!");
      // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(final ActionEvent e) {
      final String calc_text = getCalculateText().replace(" ", "");
      if (calc_text.isEmpty()) {
        addCalculateText(getResultText() + "!");
        setRotateOperator(true);
      } else {
        String lastchar = String.valueOf(calc_text.replace(" ", "").charAt(calc_text.length() - 1));
        if (lastchar.equals("!")) {
          addCalculateText(getLastOp().replace("*", "×").replace("/", "÷") + " " + getResultText() + "!");
          setRotateOperator(true);
        } else if (lastchar.equals(")")) {
          addCalculateText("!");
          setRotateOperator(true);
        } else {
          addCalculateText(getResultText() + "!");
          setRotateOperator(true);
        }
      }
    }
  }

  protected class power extends MyOpAction {

    public power() {
      super("x^");
      // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(final ActionEvent e) {
      setLastOp("^");
      if (getRemoveValue()) {
        setCalculateText("");
        if (isInvalidInput(getResultText())) {
          setResultText("0");
        }
        setRemoveValue(false);
      }
      if (!getRotateOperator()) {
        setRemoveValue(true);
        setLastNumber(getResultText());
        if (getCalculateText().contains("=")) {
          setCalculateText(getResultText() + " ^");
        } else {
          addCalculateText(getResultText() + " ^");
        }
        setRemoveValue(true);
      } else {
        if (getCalculateText().replace(" ", "").charAt(getCalculateText().replace(" ", "").length() - 1) == ')') {
          addCalculateText("^");
        } else {
          addCalculateText("^");
        }
        setRemoveValue(true);
        setRotateOperator(false);
      }
    }
  }

  protected class root extends MyOpAction {

    public root() {
      super("√x");
      // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(final ActionEvent e) {
      if (getRemoveValue()) {
        setCalculateText("");
        setResultText("0");
        setRemoveValue(false);
      }
      if (!getRotateOperator()) {
        addCalculateText("√(");
      } else if (!getCalculateText().isEmpty()) {
        addCalculateText(getLastOp() + " √(");
      }
      setRemoveValue(true);
      // setRotateOperator(true);
    }
  }

  protected abstract class MyOpAction extends AbstractAction {

    private static final long serialVersionUID = 1L;
    private final KeyStroke[] keyStrokes;

    public MyOpAction(final String name, final int key) {
      this(name, KeyStroke.getKeyStroke((char) key));
    }

    public MyOpAction(final String name, final KeyStroke... keyStrokes) {
      super(name);
      this.keyStrokes = keyStrokes;
    }

    public KeyStroke[] getKeyStrokes() {
      return this.keyStrokes;
    }
  }

  // handle negative
  protected class NegativeAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public NegativeAction() {
      super("±", KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      final char firstchar = getResultText().charAt(0);
      if (String.valueOf(firstchar).equals("-")) {
        setResultText(getResultText().substring(1));
      } else {
        setResultText("-" + getResultText());
      }
    }
  }

  // handle Number
  protected class NumberAction extends MyOpAction {

    private static final long serialVersionUID = 1L;
    private final String      num;

    public NumberAction(final String op, final int key) {
      this(op, KeyStroke.getKeyStroke(key, 0));
    }

    public NumberAction(final String num, final KeyStroke... keyStrokes) {
      super(num, keyStrokes);
      this.num = num;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      if (getResultText().contains("Ungültige Eingabe") || getResultText().contains("Unendlich") || getCalculateText().contains("Ungültige Eingabe")
          || getCalculateText().contains("Unendlich")) {
        setCalculateText("");
        setRemoveValue(true);
      }
      if (getRemoveValue()) {
        if (getCalculateText().contains("Ungültige Eingabe") || getCalculateText().contains("Unendlich")) {
          setCalculateText("");
        } else if (getCalculateText().contains("=")) {
          setCalculateText("");
        }
        setResultText("0");
        setRemoveValue(false);
      }
      if (getResultText().replace(".", "").replace(",", "").length() < 13) {
        if (getResultText().equals("0") || getResultText().equals("-0")) {
          setResultText(getResultText().replace("0", num));
        } else {
          addResultText(num);
        }
      }
      final String originalText = getResultText();
      final int index = originalText.indexOf(',');
      final String result;
      final String result2;
      if (index != -1) {
        result = originalText.substring(0, index).replace(".", "");
        result2 = originalText.substring(index);
      } else {
        result = originalText.replace(".", "");
        result2 = "";
      }
      final DecimalFormat decimalFormat = new DecimalFormat("#,###");
      final String formattedNumber = decimalFormat.format(Long.parseLong(result));
      setResultText(formattedNumber + result2);
      final int len = getResultText().replace(",", "").replace(".", "").length();
      int fontSize = 45;
      if (len >= 12) {
        fontSize = 40;
        if (len >= 14) {
          fontSize = 35;
        }
      }
      resultlabel.setFont(new Font("Serif", Font.PLAIN, fontSize));
    }
  }

  // handle operations
  protected class OperationAction extends MyOpAction {

    private static final long serialVersionUID = 1L;
    private final String      op;

    public OperationAction(final String op, final int key) {
      this(op, KeyStroke.getKeyStroke(key, 0));
    }

    public OperationAction(final String op, final KeyStroke... keyStrokes) {
      super(op, keyStrokes);
      this.op = op;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      setLastOp(op);
      setLastNumber(getResultText());
      if (getCalculateText().contains("=")) {
        if (getRemoveAfterPaste()) {
          setCalculateText(" " + op + " ");
        } else {
          setCalculateText(getResultText() + " " + op + " ");
        }
      } else {
        if (getRemoveAfterPaste()) {
          addCalculateText(" " + op + " ");
        } else {
          addCalculateText(getResultText() + " " + op + " ");
        }
      }
      setRemoveAfterPaste(false);
      setRemoveValue(true);
    }
  }

  protected class PasteFromClipboardAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public PasteFromClipboardAction(final String op, final int key) {
      this(op, KeyStroke.getKeyStroke(key, 0));
    }

    public PasteFromClipboardAction(final String op, final KeyStroke... keyStrokes) {
      super(op, keyStrokes);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      String data = null;
      try {
        data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        try {
          if (!data.equals("") && !data.equals(" ") && !data.equals("0")) {
            setRemoveAfterPaste(true);
            if (data.length() < 18) {
              RechenMaxCalculator.calculate("1+" + data);
              if (data.contains("+") || data.contains("-") || data.contains("*") || data.contains("/")) {
                if (data.startsWith("-") && !data.contains("+") && !data.contains("*") && !data.contains("/")) {
                  setResultText(data);
                } else {
                  addCalculateText(data);
                }
              } else if (getResultText() == "0") {
                setResultText(data);
              } else {
                if (getRemoveValue()) {
                  setResultText(data);
                } else {
                  if (getResultText().length() < 17) {
                    addResultText(data);
                  }
                }
              }
            } else {
              resultlabel.setFont(new Font("Serif", Font.PLAIN, 41));
              setResultText("Ungültige Eingabe");
            }
          }
        } catch (final Exception ex) {
          resultlabel.setFont(new Font("Serif", Font.PLAIN, 41));
          setResultText("Ungültige Eingabe");
        }
      } catch (final Exception ex) {
        ex.printStackTrace();
      }
      // check length
      final int len = getResultText().length();
      if (len >= 14 && getResultText() != "Ungültige Eingabe") {
        resultlabel.setFont(new Font("Serif", Font.PLAIN, 40));
        if (len >= 16 && getResultText() != "Ungültige Eingabe") {
          resultlabel.setFont(new Font("Serif", Font.PLAIN, 35));
        }
      }
    }
  }

  private static final long  serialVersionUID = 3375903438785223753L;
  protected static float     LARGE_FONT       = 19f;
  protected static float     SMALL_FONT       = 13f;
  protected static Dimension LARGE_BTN        = new Dimension(50, 50);
  protected static Dimension SMALL_BTN        = new Dimension(30, 15);

  public static void main(final String[] args) {
    final JFrame f = new JFrame();
    f.getContentPane().add(new RechenMaxUI());
    f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    f.setSize(new Dimension(410, 650));
    // f.setResizable(false);
    f.setMinimumSize(new Dimension(410, 650));
    f.setTitle("RechenMax");
    f.setLocationRelativeTo(null);
    SwingUtilities.invokeLater(() -> f.setVisible(true));
  }

  private JLabel  calculatelabel;
  private JLabel  resultlabel;
  // Remove Number Value
  private boolean removevalue      = false;
  // get and set last number from user input
  private String  last_number      = "0";
  // get and set last operation from user input
  private String  last_op          = "+";
  private boolean removeafterpaste = false;
  private boolean rotateOperator   = false;
  private boolean isNotation       = false;

  // create Buttons
  public RechenMaxUI() {
    initialize();
  }

  public void addCalculateText(final char c) {
    setCalculateText(calculatelabel.getText() + c);
  }

  public void addCalculateText(final String s) {
    setCalculateText(calculatelabel.getText() + s);
  }

  public void addResultText(final char c) {
    setResultText(getResultText() + c);
  }

  public void addResultText(final String s) {
    setResultText(getResultText() + s);
  }

  protected JComponent createButton(final MyOpAction a) {
    return createButton(a, LARGE_FONT, null, true);
  }

  protected JComponent createButton(final MyOpAction a, final boolean large) {
    return createButton(a, null, null, large);
  }

  protected JComponent createButton(final MyOpAction a, final float size) {
    return createButton(a, size, null, null);
  }

  protected JComponent createButton(final MyOpAction a, final float size, final boolean large) {
    return createButton(a, size, null, large);
  }

  protected JComponent createButton(final MyOpAction a, final Float size, final Integer style, final Boolean large) {
    final JButton b = new JButton(a);
    final JPanel p = new JPanel(new BorderLayout());
    p.add(b, BorderLayout.CENTER);
    // Facets
    if (size != null) b.setFont(b.getFont().deriveFont(size));
    if (style != null) b.setFont(b.getFont().deriveFont(style));
    if (large != null) b.setPreferredSize(large ? LARGE_BTN : SMALL_BTN);
    // Styling
    b.setBorderPainted(false);
    b.setFocusPainted(false);
    b.setContentAreaFilled(false);
    b.setFocusable(false);
    b.setOpaque(false);
    final float s = b.getFont().getSize2D();
    final Border raisedBevelBorder = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.GRAY);
    final EmptyBorder emptyBorder = new EmptyBorder(1, 1, 1, 1);
    p.setBorder(emptyBorder);
    b.getModel().addChangeListener(new ChangeListener() {

      @Override
      public void stateChanged(final ChangeEvent e) {
        final ButtonModel model = (ButtonModel) e.getSource();
        if (model.isRollover()) {
          for (int x = 0; x != 10; x++) {
            b.setFont(b.getFont().deriveFont(s + 7));
            p.setBorder(raisedBevelBorder);
          }
        } else {
          b.setFont(b.getFont().deriveFont(s));
          p.setBorder(emptyBorder);
        }
      }
    });
    // Key bindings
    final KeyStroke[] keys = a.getKeyStrokes();
    if (keys != null && keys.length > 0) {
      final String name = a.getValue(Action.NAME).toString();
      final InputMap im = b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
      for (final KeyStroke ks : keys) {
        im.put(ks, name);
      }
      b.getActionMap().put(name, a);
    }
    return p;
  }

  protected JComponent createButton(final MyOpAction a, final int style) {
    return createButton(a, null, style, null);
  }

  // 1st Panel (Calculate Panel)
  protected JComponent createPanel1() {
    final JPanel p = new JPanel(new BorderLayout());
    p.setBorder(BorderFactory.createEmptyBorder(5, 0, -5, 5));
    p.add(calculatelabel, BorderLayout.EAST);
    return p;
  }

  // 2nd Panel (Result Panel)
  protected JComponent createPanel2() {
    final JPanel p = new JPanel(new BorderLayout());
    p.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 5));
    p.add(resultlabel, BorderLayout.EAST);
    return p;
  }

  // 3rd Panel (Clipboard Buttons)
  protected JComponent createPanel3() {
    final JPanel p = new JPanel(new GridLayout(1, 3));
    p.add(createButton(new EmptyClipboard("MC", KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK)), SMALL_FONT, false));
    p.add(createButton(new PasteFromClipboardAction("MR", KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK)), SMALL_FONT, false));
    p.add(createButton(new CopyToClipboardAction("MS", KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK)), SMALL_FONT, false));
    return p;
  }

  // 4th Panel (Clipboard Buttons)
  protected JComponent createPanel4() {
    final JPanel p = new JPanel(new GridLayout(1, 4));
    p.add(createButton(new Sine()));
    p.add(createButton(new Cosine()));
    p.add(createButton(new Tangent()));
    p.add(createButton(new Pi()));
    return p;
  }

  // 5th Panel (Clipboard Buttons)
  protected JComponent createPanel5() {
    final JPanel p = new JPanel(new GridLayout(1, 5));
    p.add(createButton(new parenthesisOn()));
    p.add(createButton(new parenthesisOff()));
    p.add(createButton(new factorial()));
    p.add(createButton(new power()));
    p.add(createButton(new root()));
    return p;
  }

  // 6th Panel (Operations and Number Buttons)
  protected JComponent createPanel6() {
    final JPanel p = new JPanel(new GridLayout(5, 4, 0, 0));
    p.add(createButton(new EmptyAction()));
    p.add(createButton(new EmptyAllAction()));
    p.add(createButton(new BackspaceAction()));
    p.add(createButton(
        new OperationAction("÷", KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), KeyStroke.getKeyStroke(KeyEvent.VK_7, InputEvent.SHIFT_DOWN_MASK))));
    p.add(createButton(new NumberAction("7", KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0))));
    p.add(createButton(new NumberAction("8", KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0))));
    p.add(createButton(new NumberAction("9", KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0))));
    p.add(createButton(new OperationAction("×", KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0),
        KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.SHIFT_DOWN_MASK))));
    p.add(createButton(new NumberAction("4", KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0))));
    p.add(createButton(new NumberAction("5", KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0))));
    p.add(createButton(new NumberAction("6", KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0))));
    p.add(createButton(new OperationAction("-", KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0))));
    p.add(createButton(new NumberAction("1", KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0))));
    p.add(createButton(new NumberAction("2", KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0))));
    p.add(createButton(new NumberAction("3", KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0))));
    p.add(createButton(new OperationAction("+", KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0))));
    p.add(createButton(new NegativeAction()));
    p.add(createButton(new NumberAction("0", KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0))));
    p.add(createButton(new CommaAction()));
    p.add(createButton(new CalculateAction()));
    return p;
  }

  /**
   * This method checks if the input text is invalid.
   *
   * @param text
   *          The text to be checked. This should be a string containing the text input from the user or the result of a calculation.
   * @return Returns true if the text is invalid (contains "Ungültige Eingabe", "Unendlich", "Syntax Fehler", or "Domainfehler"), and false otherwise.
   */
  private boolean isInvalidInput(String text) {
    return text.contains("Ungültige Eingabe") || text.contains("Unendlich") || text.contains("Syntax Fehler") || text.contains("Domainfehler")
        || text.contains("For input string") || text.contains("Wert zu groß") || text.contains("Kein Teilen")
        || text.contains("Ungültiges Zahlenformat") || text.contains("Nicht definiert") || text.contains("Unbekannte Funktion");
  }

  // Calculate Label
  public String getCalculateText() {
    return calculatelabel.getText();
  }

  public String getLastNumber() {
    final String num = last_number.replace(".", "");
    final DecimalFormat decimalFormat = new DecimalFormat("#,###");
    final String formattedNumber = decimalFormat.format(Long.parseLong(num));
    return formattedNumber;
  }

  public String getLastOp() {
    return last_op;
  }

  public boolean getRemoveAfterPaste() {
    return removeafterpaste;
  }

  public boolean getRemoveValue() {
    return removevalue;
  }

  // Result Label
  public String getResultText() {
    return resultlabel.getText();
  }

  // create Panels
  protected void initialize() {
    // Calculator Design
    FlatLightLaf.setup();
    //
    calculatelabel = new JLabel("");
    resultlabel = new JLabel("0");
    resultlabel.setFont(new Font("Serif", Font.PLAIN, 45));
    calculatelabel.setFont(new Font("Serif", Font.PLAIN, 20));
    calculatelabel.setForeground(new Color(90, 90, 90));
    //
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(createPanel1());
    add(createPanel2());
    add(createPanel3());
    add(createPanel4());
    add(createPanel5());
    add(createPanel6());
    // Copy and Paste (Clipboard)
    final InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    final ActionMap am = getActionMap();
    // Copy
    String name = "Copy";
    KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
    im.put(ks, name);
    am.put(name, new CopyToClipboardAction(name, ks));
    // Paste
    name = "Paste";
    ks = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK);
    im.put(ks, name);
    am.put(name, new PasteFromClipboardAction(name, ks));
  }

  public void setRotateOperator(final boolean rotate) {
    rotateOperator = rotate;
  }

  public boolean getRotateOperator() {
    return rotateOperator;
  }

  public void setCalculateText(final char c) {
    calculatelabel.setText("" + c);
  }

  public void setIsNotation(final boolean val) {
    isNotation = val;
  }

  public boolean getIsNotation() {
    return isNotation;
  }

  public void setCalculateText(final String s) {
    calculatelabel.setText("" + s);
  }

  public void setLastNumber(final String s) {
    last_number = s;
  }

  public void setLastOp(final String s) {
    last_op = s;
  }

  public void setRemoveAfterPaste(final boolean b) {
    removeafterpaste = b;
  }

  public void setRemoveValue(final boolean b) {
    removevalue = b;
  }

  public void setResultText(final char c) {
    setResultText("" + c);
  }

  public void setResultText(final String s) {
    resultlabel.setText(s);
  }
}