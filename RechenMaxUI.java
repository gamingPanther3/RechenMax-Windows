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
import javax.swing.AbstractAction;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.formdev.flatlaf.FlatLightLaf;

public class RechenMaxUI extends JComponent {

  private static final long   serialVersionUID = 3375903438785223753L;
  protected static float      LARGE_FONT;
  protected static float      SMALL_FONT;
  protected static Dimension  LARGE_BTN;
  protected static Dimension  SMALL_BTN;
  private RechenMaxCalculator c;
  private JLabel              calculatelabel;
  private JLabel              resultlabel;
  private boolean             removevalue;
  private String              last_number;
  private String              last_op;
  static {
    RechenMaxUI.LARGE_FONT = 19.0f;
    RechenMaxUI.SMALL_FONT = 13.0f;
    RechenMaxUI.LARGE_BTN = new Dimension(50, 50);
    RechenMaxUI.SMALL_BTN = new Dimension(30, 15);
  }

  public static void main(final String[] args) {
    final JFrame f = new JFrame();
    f.getContentPane().add(new RechenMaxUI());
    f.setDefaultCloseOperation(2);
    f.setSize(new Dimension(335, 510));
    f.setMinimumSize(new Dimension(335, 510));
    f.setTitle("Rechen-Max");
    f.setLocationRelativeTo(null);
    SwingUtilities.invokeLater(() -> f.setVisible(true));
    final RechenMaxCalculator c = new RechenMaxCalculator();
    c.calculate("1+1*1/1-1,0");
  }

  public RechenMaxUI() {
    this.removevalue = false;
    this.last_number = "";
    this.last_op = "";
    this.initialize();
  }

  public void addCalculateText(final char c) {
    this.setCalculateText(String.valueOf(this.calculatelabel.getText()) + c);
  }

  public void addCalculateText(final String s) {
    this.setCalculateText(String.valueOf(this.calculatelabel.getText()) + s);
  }

  public void addResultText(final char c) {
    this.setResultText(String.valueOf(this.getResultText()) + c);
  }

  public void addResultText(final String s) {
    this.setResultText(String.valueOf(this.getResultText()) + s);
  }

  protected void calculate() {
    if (!this.getCalculateText().contains("=")) {
      final String calc = String.valueOf(this.getCalculateText()) + this.getResultText();
      this.setCalculateText(String.valueOf(this.getCalculateText()) + this.getResultText() + " =");
      final RechenMaxCalculator c = new RechenMaxCalculator();
      this.setResultText(c.calculate(calc));
    } else {
      final String result = String.valueOf(this.getResultText()) + " " + this.getLastOp() + " " + this.getLastNumber();
      this.setCalculateText(String.valueOf(result) + " =");
      this.setResultText(this.c.calculate(result));
    }
    final int len = this.getResultText().length();
    if (len > 17) {
      this.resultlabel.setFont(new Font("Serif", 0, 29));
    }
  }

  protected JComponent createButton(final MyOpAction a) {
    return this.createButton(a, RechenMaxUI.LARGE_FONT, null, true);
  }

  protected JComponent createButton(final MyOpAction a, final boolean large) {
    return this.createButton(a, null, null, large);
  }

  protected JComponent createButton(final MyOpAction a, final float size) {
    return this.createButton(a, size, null, null);
  }

  protected JComponent createButton(final MyOpAction a, final float size, final boolean large) {
    return this.createButton(a, size, null, large);
  }

  protected JComponent createButton(final MyOpAction a, final Float size, final Integer style, final Boolean large) {
    final JButton b = new JButton(a);
    final JPanel p = new JPanel(new BorderLayout());
    p.add(b, "Center");
    if (size != null) {
      b.setFont(b.getFont().deriveFont(size));
    }
    if (style != null) {
      b.setFont(b.getFont().deriveFont(style));
    }
    if (large != null) {
      b.setPreferredSize((large) ? RechenMaxUI.LARGE_BTN : RechenMaxUI.SMALL_BTN);
    }
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
          for (int x = 0; x != 10; ++x) {
            b.setFont(b.getFont().deriveFont(s + 7.0f));
            p.setBorder(raisedBevelBorder);
          }
        } else {
          b.setFont(b.getFont().deriveFont(s));
          p.setBorder(emptyBorder);
        }
      }
    });
    final KeyStroke[] keys = a.getKeyStrokes();
    if (keys != null && keys.length > 0) {
      final String name = a.getValue("Name").toString();
      final InputMap im = b.getInputMap(2);
      KeyStroke[] array;
      for (int length = (array = keys).length, i = 0; i < length; ++i) {
        final KeyStroke ks = array[i];
        im.put(ks, name);
      }
      b.getActionMap().put(name, a);
    }
    return p;
  }

  protected JComponent createButton(final MyOpAction a, final int style) {
    return this.createButton(a, null, style, null);
  }

  protected JComponent createPanel1() {
    final JPanel p = new JPanel(new BorderLayout());
    p.setBorder(BorderFactory.createEmptyBorder(5, 0, -5, 5));
    p.add(this.calculatelabel, "East");
    return p;
  }

  protected JComponent createPanel2() {
    final JPanel p = new JPanel(new BorderLayout());
    p.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 5));
    p.add(this.resultlabel, "East");
    return p;
  }

  protected JComponent createPanel3() {
    final JPanel p = new JPanel(new GridLayout(1, 3));
    p.add(this.createButton(new EmptyClipboard("MC", new KeyStroke[] { KeyStroke.getKeyStroke(76, 128) }), RechenMaxUI.SMALL_FONT, false));
    p.add(this.createButton(new PasteFromClipboardAction("MR", new KeyStroke[] { KeyStroke.getKeyStroke(82, 128) }), RechenMaxUI.SMALL_FONT, false));
    p.add(this.createButton(new CopyToClipboardAction("MS", new KeyStroke[] { KeyStroke.getKeyStroke(77, 128) }), RechenMaxUI.SMALL_FONT, false));
    return p;
  }

  protected JComponent createPanel4() {
    final JPanel p = new JPanel(new GridLayout(5, 4, 0, 0));
    p.add(this.createButton(new EmptyAction()));
    p.add(this.createButton(new EmptyAllAction()));
    p.add(this.createButton(new BackspaceAction()));
    p.add(this.createButton(new OperationAction("\u00f7", new KeyStroke[] { KeyStroke.getKeyStroke(111, 0), KeyStroke.getKeyStroke(55, 64) })));
    p.add(this.createButton(new NumberAction("7", new KeyStroke[] { KeyStroke.getKeyStroke(55, 0), KeyStroke.getKeyStroke(103, 0) })));
    p.add(this.createButton(new NumberAction("8", new KeyStroke[] { KeyStroke.getKeyStroke(56, 0), KeyStroke.getKeyStroke(104, 0) })));
    p.add(this.createButton(new NumberAction("9", new KeyStroke[] { KeyStroke.getKeyStroke(57, 0), KeyStroke.getKeyStroke(105, 0) })));
    p.add(this.createButton(new OperationAction("\u00d7", new KeyStroke[] { KeyStroke.getKeyStroke(106, 0), KeyStroke.getKeyStroke(521, 64) })));
    p.add(this.createButton(new NumberAction("4", new KeyStroke[] { KeyStroke.getKeyStroke(52, 0), KeyStroke.getKeyStroke(100, 0) })));
    p.add(this.createButton(new NumberAction("5", new KeyStroke[] { KeyStroke.getKeyStroke(53, 0), KeyStroke.getKeyStroke(101, 0) })));
    p.add(this.createButton(new NumberAction("6", new KeyStroke[] { KeyStroke.getKeyStroke(54, 0), KeyStroke.getKeyStroke(102, 0) })));
    p.add(this.createButton(new OperationAction("-", new KeyStroke[] { KeyStroke.getKeyStroke(109, 0), KeyStroke.getKeyStroke(45, 0) })));
    p.add(this.createButton(new NumberAction("1", new KeyStroke[] { KeyStroke.getKeyStroke(49, 0), KeyStroke.getKeyStroke(97, 0) })));
    p.add(this.createButton(new NumberAction("2", new KeyStroke[] { KeyStroke.getKeyStroke(50, 0), KeyStroke.getKeyStroke(98, 0) })));
    p.add(this.createButton(new NumberAction("3", new KeyStroke[] { KeyStroke.getKeyStroke(51, 0), KeyStroke.getKeyStroke(99, 0) })));
    p.add(this.createButton(new OperationAction("+", new KeyStroke[] { KeyStroke.getKeyStroke(107, 0), KeyStroke.getKeyStroke(521, 0) })));
    p.add(this.createButton(new NegativeAction()));
    p.add(this.createButton(new NumberAction("0", new KeyStroke[] { KeyStroke.getKeyStroke(48, 0), KeyStroke.getKeyStroke(96, 0) })));
    p.add(this.createButton(new CommaAction()));
    p.add(this.createButton(new CalculateAction()));
    return p;
  }

  public String getCalculateText() {
    return this.calculatelabel.getText();
  }

  public String getLastNumber() {
    return this.last_number;
  }

  public String getLastOp() {
    return this.last_op;
  }

  public boolean getRemoveValue() {
    return this.removevalue;
  }

  public String getResultText() {
    return this.resultlabel.getText();
  }

  protected void initialize() {
    FlatLightLaf.setup();
    this.c = new RechenMaxCalculator();
    this.c.calculate("1+1*1/1-1,0");
    this.calculatelabel = new JLabel("");
    (this.resultlabel = new JLabel("0")).setFont(new Font("Serif", 0, 45));
    this.calculatelabel.setFont(new Font("Serif", 0, 20));
    this.calculatelabel.setForeground(new Color(90, 90, 90));
    this.setLayout(new BoxLayout(this, 1));
    this.add(this.createPanel1());
    this.add(this.createPanel2());
    this.add(this.createPanel3());
    this.add(this.createPanel4());
    final InputMap im = this.getInputMap(2);
    final ActionMap am = this.getActionMap();
    String name = "Copy";
    KeyStroke ks = KeyStroke.getKeyStroke(67, 128);
    im.put(ks, name);
    am.put(name, new CopyToClipboardAction(name, new KeyStroke[] { ks }));
    name = "Paste";
    ks = KeyStroke.getKeyStroke(86, 128);
    im.put(ks, name);
    am.put(name, new PasteFromClipboardAction(name, new KeyStroke[] { ks }));
  }

  public void setCalculateText(final char c) {
    this.calculatelabel.setText(new StringBuilder().append(c).toString());
  }

  public void setCalculateText(final String s) {
    this.calculatelabel.setText(new StringBuilder().append(s).toString());
  }

  public void setLastNumber(final String s) {
    this.last_number = s;
  }

  public void setLastOp(final String s) {
    this.last_op = s;
  }

  public void setRemoveValue(final boolean b) {
    this.removevalue = b;
  }

  public void setResultText(final char c) {
    this.setResultText(new StringBuilder().append(c).toString());
  }

  public void setResultText(final String s) {
    this.resultlabel.setText(s);
  }

  protected class BackspaceAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public BackspaceAction() {
      super("\u232b", new KeyStroke[] { KeyStroke.getKeyStroke(8, 0) });
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      final String resultText = RechenMaxUI.this.getResultText();
      if (RechenMaxUI.this.getResultText() != "Ung\u00fcltige Eingabe") {
        if (!"0".equals(resultText) && !resultText.isEmpty()) {
          RechenMaxUI.this.setResultText(resultText.substring(0, resultText.length() - 1));
          if ("-".equals(RechenMaxUI.this.getResultText())) {
            RechenMaxUI.this.setResultText(String.valueOf(RechenMaxUI.this.getResultText()) + "0");
          }
        } else {
          RechenMaxUI.this.setResultText('0');
        }
        if (RechenMaxUI.this.getResultText() == "") {
          RechenMaxUI.this.setResultText('0');
        }
      }
      final int len = RechenMaxUI.this.getResultText().length();
      if (len <= 13) {
        RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 45));
      } else if (len <= 15) {
        RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 40));
      } else if (len <= 16) {
        RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 35));
      }
    }
  }

  protected class CalculateAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public CalculateAction() {
      super("=", new KeyStroke[] { KeyStroke.getKeyStroke(10, 0) });
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      RechenMaxUI.this.calculate();
    }
  }

  protected class CommaAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public CommaAction() {
      super(",", new KeyStroke[] { KeyStroke.getKeyStroke(44, 0) });
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      if (!RechenMaxUI.this.getResultText().contains(",")) {
        RechenMaxUI.this.setResultText(String.valueOf(RechenMaxUI.this.getResultText()) + ",");
      }
    }
  }

  protected class CopyToClipboardAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public CopyToClipboardAction(final RechenMaxUI this$0, final String op, final int key) {
      this(op, new KeyStroke[] { KeyStroke.getKeyStroke(key, 0) });
    }

    public CopyToClipboardAction(final String op, final KeyStroke... keyStrokes) {
      super(op, keyStrokes);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(RechenMaxUI.this.getResultText()), null);
    }
  }

  protected class EmptyAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public EmptyAction() {
      super("CE", new KeyStroke[] { KeyStroke.getKeyStroke(127, 0) });
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      RechenMaxUI.this.setResultText("0");
    }
  }

  protected class EmptyAllAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public EmptyAllAction() {
      super("C", new KeyStroke[] { KeyStroke.getKeyStroke(27, 0) });
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      RechenMaxUI.this.setResultText("0");
      RechenMaxUI.this.setCalculateText("");
    }
  }

  protected class EmptyClipboard extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public EmptyClipboard(final RechenMaxUI this$0, final String op, final int key) {
      this(op, new KeyStroke[] { KeyStroke.getKeyStroke(key, 0) });
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

    public MyOpAction(final RechenMaxUI this$0, final String name, final int key) {
      this(name, new KeyStroke[] { KeyStroke.getKeyStroke((char) key) });
    }

    public MyOpAction(final String name, final KeyStroke... keyStrokes) {
      super(name);
      this.keyStrokes = keyStrokes;
      if (keyStrokes != null && keyStrokes.length > 0) {
        this.putValue("MnemonicKey", (int) keyStrokes[0].getKeyChar());
      }
    }

    public KeyStroke[] getKeyStrokes() {
      return this.keyStrokes;
    }
  }

  protected class NegativeAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public NegativeAction() {
      super("±", new KeyStroke[] { KeyStroke.getKeyStroke(120, 0) });
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      if (RechenMaxUI.this.getResultText().indexOf("-") == -1) {
        RechenMaxUI.this.setResultText("-" + RechenMaxUI.this.getResultText());
      } else {
        RechenMaxUI.this.setResultText(RechenMaxUI.this.getResultText().substring(1));
      }
    }
  }

  protected class NumberAction extends MyOpAction {

    private static final long serialVersionUID = 1L;
    private final String      num;

    public NumberAction(final RechenMaxUI this$0, final String op, final int key) {
      this(op, new KeyStroke[] { KeyStroke.getKeyStroke(key, 0) });
    }

    public NumberAction(final String num, final KeyStroke... keyStrokes) {
      super(num, keyStrokes);
      this.num = num;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      if (RechenMaxUI.this.getResultText() == "Ung\u00fcltige Eingabe") {
        RechenMaxUI.this.setRemoveValue(true);
      }
      final boolean status = RechenMaxUI.this.getRemoveValue();
      if (status) {
        RechenMaxUI.this.setResultText("0");
        RechenMaxUI.this.setRemoveValue(false);
      }
      if (RechenMaxUI.this.getResultText().length() < 17) {
        if (RechenMaxUI.this.getResultText().length() <= 13) {
          RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 45));
        }
        RechenMaxUI.this.setRemoveValue(false);
        final String resultText = RechenMaxUI.this.getResultText();
        if ("0".equals(resultText)) {
          RechenMaxUI.this.setResultText(this.num);
        } else if (resultText.startsWith("-0") && !resultText.contains(",")) {
          RechenMaxUI.this.setResultText("-" + resultText.substring(1, 1) + this.num);
        } else {
          RechenMaxUI.this.setResultText(String.valueOf(RechenMaxUI.this.getResultText()) + this.num);
        }
        RechenMaxUI.this.setLastNumber(RechenMaxUI.this.getResultText());
      }
      final int len = RechenMaxUI.this.getResultText().length();
      if (len >= 14) {
        RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 40));
        if (len >= 16) {
          RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 35));
          if (len > 17) {
            RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 25));
          }
        }
      }
    }
  }

  protected class OperationAction extends MyOpAction {

    private static final long serialVersionUID = 1L;
    private final String      op;

    public OperationAction(final RechenMaxUI this$0, final String op, final int key) {
      this(op, new KeyStroke[] { KeyStroke.getKeyStroke(key, 0) });
    }

    public OperationAction(final String op, final KeyStroke... keyStrokes) {
      super(op, keyStrokes);
      this.op = op;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      RechenMaxUI.this.setLastOp(this.op);
      if (RechenMaxUI.this.getCalculateText().contains("=")) {
        RechenMaxUI.this.setCalculateText(String.valueOf(RechenMaxUI.this.getResultText()) + " " + this.op + " ");
      } else {
        RechenMaxUI.this.addCalculateText(String.valueOf(RechenMaxUI.this.getResultText()) + " " + this.op + " ");
      }
      RechenMaxUI.this.setRemoveValue(true);
    }
  }

  protected class PasteFromClipboardAction extends MyOpAction {

    private static final long serialVersionUID = 1L;

    public PasteFromClipboardAction(final RechenMaxUI this$0, final String op, final int key) {
      this(op, new KeyStroke[] { KeyStroke.getKeyStroke(key, 0) });
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
          if (data != "" && data != " ") {
            if (data.length() < 18) {
              RechenMaxUI.this.c.calculate("1+" + data);
              if (data.contains("+") || data.contains("-") || data.contains("*") || data.contains("/")) {
                if (data.startsWith("-") && !data.contains("+") && !data.contains("*") && !data.contains("/")) {
                  RechenMaxUI.this.setResultText(data);
                } else {
                  RechenMaxUI.this.addCalculateText(data);
                }
              } else if (RechenMaxUI.this.getResultText() == "0") {
                RechenMaxUI.this.setResultText(data);
              } else if (RechenMaxUI.this.getRemoveValue()) {
                RechenMaxUI.this.setResultText(data);
              } else if (RechenMaxUI.this.getResultText().length() < 17) {
                RechenMaxUI.this.addResultText(data);
              }
            } else {
              RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 41));
              RechenMaxUI.this.setResultText("Ung\u00fcltige Eingabe");
            }
          }
        } catch (Exception ex) {
          RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 41));
          RechenMaxUI.this.setResultText("Ung\u00fcltige Eingabe");
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      final int len = RechenMaxUI.this.getResultText().length();
      if (len >= 14 && RechenMaxUI.this.getResultText() != "Ung\u00fcltige Eingabe") {
        RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 40));
        if (len >= 16 && RechenMaxUI.this.getResultText() != "Ung\u00fcltige Eingabe") {
          RechenMaxUI.this.resultlabel.setFont(new Font("Serif", 0, 35));
        }
      }
    }
  }
}
