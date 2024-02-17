package praktikum2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
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

public class RechenMaxUI_Prank extends JComponent {

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
      calculate();
      final int len = getResultText().length();
      if (len >= 14) {
        resultlabel.setFont(new Font("Serif", Font.PLAIN, 40));
        if (len >= 16) {
          resultlabel.setFont(new Font("Serif", Font.PLAIN, 35));
          if (len > 17) {
            resultlabel.setFont(new Font("Serif", Font.PLAIN, 30));
          }
        }
      } else {
        resultlabel.setFont(new Font("Serif", Font.PLAIN, 45));
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
  private static int         windowvalue      = 1;
  private static String      position         = "middle";

  public static String getStartPositionValue() {
    return position;
  }

  public static int getWindowValue() {
    return windowvalue;
  }

  public static void main(final String[] args) {
    final JFrame f = new JFrame();
    f.getContentPane().add(new RechenMaxUI());
    class WindowEventHandler extends WindowAdapter {

      @Override
      public void windowClosing(final WindowEvent evt) {
        final int value = getWindowValue();
        final int max = value * 2;
        for (int i = 0; i < max; i++)
          mainCaller();
        setWindowValue(max);
      }
    }
    f.addWindowListener(new WindowEventHandler());
    f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    f.setSize(new Dimension(335, 510));
    f.setMinimumSize(new Dimension(335, 510));
    f.setMaximumSize(new Dimension(335, 510));
    f.setTitle("Rechen-Max");
    // open window on first load in center then randomize position of the other windows
    if (getStartPositionValue() == "middle") {
      f.setLocationRelativeTo(null);
      setStartPositionValue("random");
    } else {
      f.setLocation(ThreadLocalRandom.current().nextInt(0, Toolkit.getDefaultToolkit().getScreenSize().width - 500),
          ThreadLocalRandom.current().nextInt(0, Toolkit.getDefaultToolkit().getScreenSize().height - 500));
    }
    //
    SwingUtilities.invokeLater(() -> f.setVisible(true));
    new Thread(new Runnable() {

      @Override
      public void run() {
        // declare mouse and window pos
        try {
          Thread.sleep(200);
        } catch (final InterruptedException e) {
          e.printStackTrace();
        }
        while (true) {
          // set mouse and window position
          final int mouse_x = MouseInfo.getPointerInfo().getLocation().x;
          final int mouse_y = MouseInfo.getPointerInfo().getLocation().y;
          final int window_x = f.getLocationOnScreen().x; // Cursor on: W:880 ;M:1180 D: 300
          final int window_y = f.getLocationOnScreen().y; // Cursor on: W:200 ;M:205 D: 5
          final int distance = (int) Math
              .sqrt((window_x + 300 - mouse_x) * (window_x + 300 - mouse_x) + (window_y + 10 - mouse_y) * (window_y + 10 - mouse_y));
          final int rnd = ThreadLocalRandom.current().nextInt(1, 4 + 1);
          if (distance <= 25) {
            if (rnd == 1 && window_x < Toolkit.getDefaultToolkit().getScreenSize().width - 450) {
              f.setLocation(window_x + 100, window_y);
            } else if (rnd == 2 && window_x > 200) {
              f.setLocation(window_x - 100, window_y);
            } else if (rnd == 3 && window_y < Toolkit.getDefaultToolkit().getScreenSize().height - 450) {
              f.setLocation(window_x, window_y + 100);
            } else if (rnd == 4 && window_y > 500) {
              f.setLocation(window_x, window_y - 100);
            }
          } else if (distance <= 35) {
            if (rnd == 1 && window_x < Toolkit.getDefaultToolkit().getScreenSize().width - 450) {
              f.setLocation(window_x + 50, window_y);
            } else if (rnd == 2 && window_x > 200) {
              f.setLocation(window_x - 50, window_y);
            } else if (rnd == 3 && window_y < Toolkit.getDefaultToolkit().getScreenSize().height - 450) {
              f.setLocation(window_x, window_y + 50);
            } else if (rnd == 4 && window_y > 500) {
              f.setLocation(window_x, window_y - 50);
            }
          }
        }
      }
    }).start();
  }

  static void mainCaller() {
    RechenMaxUI_Prank.main(null);
  }

  public static void setStartPositionValue(final String value) {
    position = value;
  }

  public static void setWindowValue(final int num) {
    windowvalue = num;
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

  // create Buttons
  public RechenMaxUI_Prank() {
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

  protected void calculate() {
    // if "=" is pressed
    if (!getCalculateText().contains("=")) {
      setLastNumber(getResultText());
      setCalculateText(getCalculateText() + getResultText() + " =");
      final String calc = getCalculateText();
      if (calc.contains("+") || calc.contains("-") || calc.contains("*") || calc.contains("/")) {
        setResultText(RechenMaxCalculator.calculate(getCalculateText()));
      } else {
        setResultText(RechenMaxCalculator.calculate(getCalculateText()));
      }
    } else {
      if (getLastOp() != "") {
        setCalculateText(getResultText() + " " + getLastOp() + " " + getLastNumber() + " =");
      } else {
        setCalculateText(getResultText() + " =");
      }
      setResultText(RechenMaxCalculator.calculate(getResultText() + " " + getLastOp() + " " + getLastNumber()));
    }
    setRemoveValue(true);
    final int index = getResultText().indexOf(',');
    if (index != -1) {
      final String result = getResultText().substring(0, index).replace(".", "").replace(",", "");
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
    final int len = getResultText().replace(",", "").replace(".", "").length();
    if (len > 17) {
      resultlabel.setFont(new Font("Serif", Font.PLAIN, 30));
    }
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

  // 4th Panel (Operations and Number Buttons)
  protected JComponent createPanel4() {
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
    RechenMaxCalculator.calculate("1+1*1/1-1,0");
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

  public void setCalculateText(final char c) {
    calculatelabel.setText("" + c);
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