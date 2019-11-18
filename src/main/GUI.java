package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import exception.CSVFormatException;
import exception.DeliveryException;
import exception.StockException;
import stock.Item;

public class GUI extends JFrame implements ActionListener, Runnable {
	
	private static final long serialVersionUID = -7031008862559936404L; 
	public static final int WIDTH = 900;
	public static final int HEIGHT = 400;
	
	private JPanel panelButton, panelDisplay, panelException;
	private JScrollPane paneTable;
	private JTable table;
	private JButton btnLoadItems, btnLoadSaleLog, btnLoadManifest, btnExportManifest;
	private JTextArea textArea;
	
	private Store store = Store.getInstance();
	private Logic logic = new Logic();
	
	public GUI(String title) {
		super(title);
	}

	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(new BorderLayout());
		
		layoutDisplayPanel();
		layoutButtonPanel();
		layoutTablePane();
		
		panelException = createPanel(Color.WHITE);
		
		this.getContentPane().add(panelButton, BorderLayout.SOUTH);
		this.getContentPane().add(panelDisplay, BorderLayout.NORTH);
		this.getContentPane().add(paneTable, BorderLayout.CENTER);
		
		repaint();
		this.setVisible(true);
	}
	
	private void layoutDisplayPanel() {
		textArea = createTextArea("$100,000.00");
		panelDisplay = createPanel(Color.WHITE);
		
		panelDisplay.setLayout(new BorderLayout());
		panelDisplay.add(textArea, BorderLayout.CENTER);
	}
	
	private void layoutButtonPanel() {
		btnLoadItems = createButton("Load Item Properties");
		btnLoadSaleLog = createButton("Load Sale Log");
		btnLoadManifest = createButton("Load Manifest");
		btnExportManifest = createButton("Export Manifest");
		
		panelButton = createPanel(Color.DARK_GRAY);
		
		panelButton.setLayout(new FlowLayout());
		panelButton.add(btnLoadItems);
		panelButton.add(btnLoadSaleLog);
		panelButton.add(btnLoadManifest);
		panelButton.add(btnExportManifest);
	}
	
	private void layoutTablePane() {
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		paneTable = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
	}
	
	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	}
	
	private JPanel createPanel(Color c) {
		JPanel panel = new JPanel();
		panel.setBackground(c);
		return panel;
	}
	
	private JTextArea createTextArea(String name) {
		JTextArea display = new JTextArea(name);
		display.setEditable(false);
		display.setLineWrap(true);
		display.setFont(new Font("Arial", Font.BOLD, 24));
		display.setBorder(BorderFactory.createEtchedBorder());
		return display;
	}
	
	@Override
	public void run() {
		createGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn == btnLoadItems || btn == btnLoadSaleLog || btn == btnLoadManifest) {
			String filePath = getFilePath("Open");
			if(btn == btnLoadItems) {
				try {
					logic.loadItems(filePath);
				} catch (IOException | CSVFormatException | StockException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if (btn == btnLoadSaleLog) {
				try {
					logic.loadSaleLog(filePath);
				} catch (IOException | CSVFormatException | StockException | DeliveryException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if (btn == btnLoadManifest) {
				try {
					logic.loadManifest(filePath);
				} catch (StockException | IOException | DeliveryException | CSVFormatException e1) {
					e1.printStackTrace();
				}
			}
		}else if (btn == btnExportManifest) {
			String filePath = getFilePath("Save");
			try {
				logic.exportManifest(filePath);
			} catch (StockException | DeliveryException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			updateGUI();
		} catch (StockException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private void updateGUI() throws StockException {
		textArea.setText(String.format("$%,.2f", store.getCapital()));
		String[] columnNames = {"Name", "Quantity", "Cost($)", "Price($)", 
				"Reorder point", "Reorder quantity", "Temperature (*C)"
			};
		DefaultTableModel model = new DefaultTableModel();
		for(String column : columnNames) {
			model.addColumn(column);
		}
		for(java.util.Map.Entry<Item, Integer> entry : store.getInventory().entrySet()) {
			Item item = entry.getKey();
			int quantity = entry.getValue();
			model.addRow(new Object[] {
					item.getName(),
					quantity,
					item.getCost(),
					item.getPrice(),
					item.getReorderPoint(),
					item.getReorderAmount(),
					item.isFreshFood() ? item.getTemp() : "N/A"});
		}
		table.setModel(model);
		table.setFillsViewportHeight(true);	
	}

	private String getFilePath(String type) {
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
	    fc.setCurrentDirectory(new java.io.File("."));
		int returnVal = fc.showDialog(this, type);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile().getAbsolutePath();
		}
		return "";
	}
	
}
