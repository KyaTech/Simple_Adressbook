package com.company;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;

import javafx.scene.control.ListCell;

public class ContactsView extends JPanel {

	private JScrollPane scrollpane;
	private Kontakt[] contacts;
	private JList<Kontakt> listView;

	public ContactsView() {
		super();

		listView = new JList<>();

		listView.setLayoutOrientation(JList.VERTICAL);
		listView.setDragEnabled(false);
		listView.setCellRenderer(new ContactRenderer());
		listView.setBackground(getBackground());
		listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollpane = new JScrollPane(listView);
		scrollpane.setBorder(null);

		this.add(scrollpane);
		this.setVisible(true);

	}

	public Kontakt getSelectedContact() {
		if (listView.getSelectedIndex() != -1 && listView.getSelectedIndex() < contacts.length && listView.getSelectedIndex() >= 0) {
			return contacts[listView.getSelectedIndex()];
		}
		return null;
	}

	public void selectContact(Kontakt toSelect) {
		if (toSelect != null) {
			int counter = 0;
			for (Kontakt contact : contacts) {
				if (contact.equals(toSelect)) {
					listView.setSelectedIndex(counter);
				}
				counter++;
			}
		}
	}

	public void updateContacts(Kontakt[] contacts) {
		Kontakt selected = getSelectedContact();

		this.contacts = contacts;
		listView.setListData(contacts);

		if (selected != null) {

			int counter = 0;
			for (Kontakt contact : contacts) {
				if (contact.equals(selected)) {
					listView.setSelectedIndex(counter);
				}
				counter++;
			}

		}



	}

	public void addListSelectionListener(ListSelectionListener listener) {
		listView.addListSelectionListener(listener);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		scrollpane.setBounds(20, 20, getWidth() - 40, getHeight() - 40);
	}

	private class ContactRenderer extends JTextArea implements ListCellRenderer<Kontakt> {

		private final Font font = new Font(null,Font.PLAIN, 16);

		@Override
		public Component getListCellRendererComponent(JList<? extends Kontakt> list, Kontakt value, int index, boolean isSelected, boolean cellHasFocus) {

			setText(String.format("%s %s",value.gibVorname(),value.gibName()));
			setFont(font);
			setBorder(BorderFactory.createEmptyBorder(10,5,10,5));


			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			return this;
		}

	}

}
