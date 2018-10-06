package be.dagal.java.kraken.ui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CurrencySummaryList extends JList<CurrencySummary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Border noFocusBorder = new EmptyBorder(1,1,1,1);
	
	public CurrencySummaryList() {
		setCellRenderer(new CellRenderer());
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int index=locationToIndex(e.getPoint());
				if (index!=-1) {
					CurrencySummary currencySummary = (CurrencySummary) getModel().getElementAt(index);
					currencySummary.setSelected(!currencySummary.isSelected());
					repaint();
				}
			}
		});
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public CurrencySummaryList(DefaultListModel<CurrencySummary> assetInfoListModel) {
		setModel(assetInfoListModel);
		setCellRenderer(new CellRenderer());
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int index=locationToIndex(e.getPoint());
				if (index!=-1) {
					CurrencySummary currencySummary = (CurrencySummary) getModel().getElementAt(index);
					currencySummary.setSelected(!currencySummary.isSelected());
					repaint();
				}
			}
		});
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	protected class CellRenderer implements ListCellRenderer<CurrencySummary>{
		public Component getListCellRendererComponent(JList<? extends CurrencySummary> list, CurrencySummary value, int index, boolean isSelected, boolean cellHasFocus) {
			CurrencySummary currencySummary = value;
			
			// Drawing CurrencySummary, change the appearance here
			currencySummary.setBackground(isSelected?getSelectionBackground():getBackground());
			currencySummary.setForeground(isSelected?getSelectionForeground():getForeground());;
			currencySummary.setEnabled(isEnabled());;
			currencySummary.setFont(getFont());;
			//currencySummary.setFocusPainted(false);
			//currencySummary.setBorderPainted(true);
			currencySummary.setBorder(isSelected?UIManager.getBorder("List.focusCellHighlightBorder"):noFocusBorder);;
			return currencySummary;
		}
	}
}
