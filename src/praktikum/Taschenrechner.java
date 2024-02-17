package praktikum;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Taschenrechner extends JComponent {

	protected class MyOpAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private final String op;

		public MyOpAction(final String op) {
			super(op);
			this.op = op;
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			click(op);
		}
	}

	private static final long serialVersionUID = 1L;

	public static void main(final String[] args) {
		final JFrame f = new JFrame();
		f.getContentPane().add(new Taschenrechner());
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setSize(new Dimension(335, 510));
		SwingUtilities.invokeLater(() -> f.setVisible(true));
	}

	private JLabel label = new JLabel("5");
	
	public Taschenrechner() {
		initialize();
	}

	protected void click(final String op) {
		System.out.println("Clicked: " + op);
		if(op == "1" || op == "2" || op == "3" || op == "4" || op == "5" || op == "6" || op == "7") {
			label.setText(label.getText() + op);
		}
	}
	
	protected void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(createPane3());
		add(createPane2());
		add(createPane1());
	}
	
	protected JComponent createPane3() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.add(label, BorderLayout.EAST);
		return p;
	}
	protected JComponent createPane2() {
		JPanel p = new JPanel(new GridLayout(1, 5));
		return p;
	}
	protected JComponent createPane1() {
		JPanel p = new JPanel(new GridLayout(6, 4));

		p.add(new JButton(new MyOpAction("%")));
		p.add(new JButton(new MyOpAction("CE")));
		p.add(new JButton(new MyOpAction("C")));
		p.add(new JButton(new MyOpAction("<-")));
		p.add(new JButton(new MyOpAction("1/x")));
		p.add(new JButton(new MyOpAction("x^2")));
		p.add(new JButton(new MyOpAction("√")));
		p.add(new JButton(new MyOpAction("/")));
		p.add(new JButton(new MyOpAction("7")));
		p.add(new JButton(new MyOpAction("8")));
		p.add(new JButton(new MyOpAction("9")));
		p.add(new JButton(new MyOpAction("X")));
		p.add(new JButton(new MyOpAction("4")));
		p.add(new JButton(new MyOpAction("5")));
		p.add(new JButton(new MyOpAction("6")));
		p.add(new JButton(new MyOpAction("-")));
		p.add(new JButton(new MyOpAction("1")));
		p.add(new JButton(new MyOpAction("2")));
		p.add(new JButton(new MyOpAction("3")));
		p.add(new JButton(new MyOpAction("+")));
		p.add(new JButton(new MyOpAction("+/-")));
		p.add(new JButton(new MyOpAction("0")));
		p.add(new JButton(new MyOpAction(",")));
		p.add(new JButton(new MyOpAction("=")));
		return p;
	}
}