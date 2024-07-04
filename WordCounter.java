import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.*;

public class WordCounter {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		JFrame frame = new JFrame("Word Counter");
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		ImageIcon icon = new ImageIcon("image.png");
		Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		frame.setIconImage(scaledImage);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textArea.setBackground(new Color(240, 248, 255));
		textArea.setForeground(new Color(25, 25, 112));

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(50, 50, 500, 300);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.add(scrollPane);

		JButton countButton = new JButton("Count") {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(100, 149, 237));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				super.paintComponent(g2);
				g2.dispose();
			}
		};
		countButton.setBounds(240, 370, 120, 40);
		countButton.setForeground(Color.WHITE);
		countButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		countButton.setContentAreaFilled(false);
		countButton.setFocusPainted(false);
		countButton.setBorder(new EmptyBorder(10, 20, 10, 20));
		frame.add(countButton);

		JLabel wordCountLabel = new JLabel("Word Count: 0", SwingConstants.RIGHT);
		wordCountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		wordCountLabel.setForeground(new Color(25, 25, 112)); // Midnight Blue text
		wordCountLabel.setBounds(450, 420, 120, 30);
		frame.add(wordCountLabel);

		countButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textArea.getText();
				int wordCount = countWords(text);
				wordCountLabel.setText("Word Count: " + wordCount);
			}
		});

		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension size = frame.getSize();
				scrollPane.setBounds(50, 50, size.width - 100, size.height - 200);
				countButton.setBounds(size.width / 2 - 60, size.height - 120, 120, 40);
				wordCountLabel.setBounds(size.width - 170, size.height - 80, 120, 30);

				int fontSize = Math.max(12, size.width / 50);
				textArea.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
				countButton.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
				wordCountLabel.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
			}
		});

		frame.setVisible(true);
	}

	private static int countWords(String text) {
		if (text == null || text.isEmpty()) {
			return 0;
		}
		String[] words = text.trim().split("\\s+");
		return words.length;
	}
}