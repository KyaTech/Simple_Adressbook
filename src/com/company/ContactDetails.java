package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.*;

public class ContactDetails extends JPanel {

	private Kontakt current;
	private final JTextField vorname;
	private final JTextField nachname;
	private final JTextField telefon;
	private final JTextField email;
	private final JToggleButton edit;
	private final JButton delete;
	private ActionListener listener;


	public ContactDetails() {
		super();
		setLayout(null);

		ImageIcon editIcon = new ImageIcon("res/baseline_edit_black_48dp.png");
		edit = new JToggleButton(editIcon);
		edit.setBackground(Color.WHITE);
		edit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		edit.setVisible(false);
		add(edit);


		ImageIcon deleteIcon = new ImageIcon("res/baseline_delete_black_48dp.png");
		delete = new JButton(deleteIcon);
		delete.setBackground(Color.WHITE);
		delete.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		delete.setVisible(false);
		add(delete);

		vorname = new JTextField();
		vorname.setEditable(false);
		vorname.setBorder(null);
		vorname.addActionListener(e -> edit.doClick());
		vorname.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Vorname"));
		add(vorname);

		nachname = new JTextField();
		nachname.setEditable(false);
		nachname.setBorder(null);
		nachname.addActionListener(e -> edit.doClick());
		nachname.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Nachname"));
		add(nachname);

		email = new JTextField();
		email.setEditable(false);
		email.setBorder(null);
		email.addActionListener(e -> edit.doClick());
		email.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "E-Mail"));
		add(email);

		telefon = new JTextField();
		telefon.setEditable(false);
		telefon.setBorder(null);
		telefon.addActionListener(e -> edit.doClick());
		telefon.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Telefon"));
		add(telefon);



		edit.addItemListener(e -> {
			int state = e.getStateChange();
			if (state == ItemEvent.SELECTED) {
				vorname.setEditable(true);
				nachname.setEditable(true);
				telefon.setEditable(true);
				email.setEditable(true);
			} else if (state == ItemEvent.DESELECTED) {
				vorname.setEditable(false);
				nachname.setEditable(false);
				telefon.setEditable(false);
				email.setEditable(false);

				current.setVorname(vorname.getText());
				current.setName(nachname.getText());
				current.setTelefon(telefon.getText());
				current.setEmail(email.getText());

				listener.actionPerformed(new ActionEvent(current, ActionEvent.ACTION_PERFORMED, "Update"));
			}
		});

		delete.addActionListener(e -> {
			listener.actionPerformed(new ActionEvent(current, ActionEvent.ACTION_PERFORMED, "Delete"));
			showContact(null);
		});

		makeVisible(false);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		vorname.setBounds(30, 30, getWidth() - 60, 60);
		nachname.setBounds(30, 120, getWidth() - 60, 60);
		email.setBounds(30, 220, getWidth() - 60, 60);
		telefon.setBounds(30, 320, getWidth() - 60, 60);

		edit.setBounds(getWidth() - 70, getHeight() - 70, 40, 40);

		ImageIcon editIcon = (ImageIcon) edit.getIcon();
		Image imgEdit = editIcon.getImage();
		Image newimgEdit = imgEdit.getScaledInstance(edit.getWidth(), edit.getHeight(), java.awt.Image.SCALE_SMOOTH);
		editIcon = new ImageIcon(newimgEdit);

		edit.setIcon(editIcon);

		delete.setBounds(getWidth() - 120, getHeight() - 70, 40, 40);

		ImageIcon deleteIcon = (ImageIcon) delete.getIcon();
		Image imgDelete = deleteIcon.getImage();
		Image newimgDelete = imgDelete.getScaledInstance(delete.getWidth(), delete.getHeight(), java.awt.Image.SCALE_SMOOTH);
		deleteIcon = new ImageIcon(newimgDelete);

		delete.setIcon(deleteIcon);

	}

	public void setActionListener(ActionListener listener) {
		this.listener = listener;
	}

	public void showContact(Kontakt contact) {
		if (contact != null) {
			if (edit.isSelected()) {
				edit.doClick();
			}

			this.current = contact;

			vorname.setText(contact.gibVorname());
			nachname.setText(contact.gibName());
			telefon.setText(contact.gibTelefon());
			email.setText(contact.gibEmail());


			makeVisible(true);
		} else {
			vorname.setText("");
			nachname.setText("");
			telefon.setText("");
			email.setText("");

			makeVisible(false);
		}
	}

	public Kontakt getCurrentContact() {
		return current;
	}

	private void makeVisible(boolean visible) {
		if (visible) {
			vorname.setVisible(true);
			nachname.setVisible(true);
			telefon.setVisible(true);
			email.setVisible(true);
			edit.setVisible(true);
			delete.setVisible(true);
		} else {
			vorname.setVisible(false);
			nachname.setVisible(false);
			telefon.setVisible(false);
			email.setVisible(false);
			edit.setVisible(false);
			delete.setVisible(false);
		}
	}

}
