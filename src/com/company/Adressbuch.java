package com.company;

import java.awt.*;

import javax.swing.*;

public class Adressbuch {

	private Verwaltung verwaltung = new Verwaltung();

	public Adressbuch() {
		JFrame jFrame = new JFrame("Adressbuch");
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.getContentPane().setPreferredSize(new Dimension(1280, 720));
		jFrame.pack();
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);

		JPanel root = new JPanel();
		root.setLayout(null);
		root.setBounds(0, 0, (int) jFrame.getContentPane().getPreferredSize().getWidth(), (int) jFrame.getContentPane().getPreferredSize().getHeight());


		JPanel contactInput = new JPanel();
		contactInput.setLayout(null);
		contactInput.setBounds(10, 10, 300, root.getHeight() - 20);
		contactInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Kontakt"));
		root.add(contactInput);


		ContactDetails contactDetails = new ContactDetails();
		contactDetails.setLayout(null);
		contactDetails.setBounds(330, 10, 420, root.getHeight() - 20);
		contactDetails.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Kontaktdetails"));
		root.add(contactDetails);


		ContactsView contactsView = new ContactsView();
		contactsView.setLayout(null);
		contactsView.setBounds(770, 10, 500, root.getHeight() - 20);
		contactsView.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Adressbuch"));
		contactsView.updateContacts(verwaltung.asArray());
		root.add(contactsView);

		//create Contact

		JButton add = new JButton("Hinzufuegen");
		int add_width = (int) (contactInput.getWidth() * (0.6));
		add.setBounds(((contactInput.getWidth() / 2) - (add_width / 2)), 400, add_width, 40);
		add.setBackground(Color.WHITE);
		contactInput.add(add);

		JButton suchen = new JButton("Suchen");
		int suchen_width = (int) (contactInput.getWidth() * (0.6));
		suchen.setBounds(((contactInput.getWidth() / 2) - (suchen_width / 2)), 480, suchen_width, 40);
		suchen.setBackground(Color.WHITE);
		contactInput.add(suchen);

		JLabel vornameLabel = new JLabel("Vorname");
		vornameLabel.setBounds(10, 10, contactInput.getWidth() - 20, 30);
		contactInput.add(vornameLabel);

		JTextField vorname = new JTextField(30);
		vorname.setBounds(10, 40, contactInput.getWidth() - 20, 30);
		vorname.addActionListener(e -> add.doClick());
		contactInput.add(vorname);


		JLabel nachnameLabel = new JLabel("Nachname");
		nachnameLabel.setBounds(10, 100, contactInput.getWidth(), 30);
		contactInput.add(nachnameLabel);

		JTextField nachname = new JTextField(30);
		nachname.setBounds(10, 140, contactInput.getWidth() - 20, 30);
		nachname.addActionListener(e -> add.doClick());
		contactInput.add(nachname);


		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(10, 200, contactInput.getWidth() - 20, 30);
		contactInput.add(emailLabel);

		JTextField email = new JTextField(30);
		email.setBounds(10, 240, contactInput.getWidth() - 20, 30);
		email.addActionListener(e -> add.doClick());
		contactInput.add(email);

		JLabel telefonLabel = new JLabel("Telefon");
		telefonLabel.setBounds(10, 300, contactInput.getWidth() - 20, 30);
		contactInput.add(telefonLabel);

		JTextField telefon = new JTextField(30);
		telefon.setBounds(10, 340, contactInput.getWidth() - 20, 30);
		telefon.addActionListener(e -> add.doClick());
		contactInput.add(telefon);

		// functional
		add.addActionListener(e -> {

			if (!(nachname.getText().trim().isEmpty() && vorname.getText().trim().isEmpty())) {

				Kontakt neuerKontakt = new Kontakt(nachname.getText().trim(), vorname.getText().trim(), telefon.getText().trim(), email.getText().trim());
				verwaltung.hinzufuegenUndSpeichern(neuerKontakt);

				nachname.setText("");
				vorname.setText("");
				email.setText("");
				telefon.setText("");

				contactsView.updateContacts(verwaltung.asArray());

				vorname.requestFocus();

			}

		});

		suchen.addActionListener(e -> {

			if (!(nachname.getText().trim().isEmpty() && vorname.getText().trim().isEmpty())) {

				Kontakt neuerKontakt = new Kontakt(nachname.getText().trim(), vorname.getText().trim(), telefon.getText().trim(), email.getText().trim());
				Kontakt kontakt = verwaltung.sucheKontakt(neuerKontakt);
				contactDetails.showContact(kontakt);
				contactsView.selectContact(kontakt);

			}
		});

		contactsView.addListSelectionListener(e -> {
			Kontakt selectedContact = contactsView.getSelectedContact();

			if (selectedContact != null && !selectedContact.equals(contactDetails.getCurrentContact())) {
				contactDetails.showContact(selectedContact);
			}
		});

		contactDetails.setActionListener(e -> {
			if (e.getActionCommand().equals("Update")) {
				contactsView.updateContacts(verwaltung.asArray());
				verwaltung.speichereAenderung();
			} else if (e.getActionCommand().equals("Delete")) {
				verwaltung.loeschen(contactDetails.getCurrentContact());
				contactsView.updateContacts(verwaltung.asArray());
			}
		});


		jFrame.add(root);
		jFrame.setVisible(true);

	}

	public static void main(String[] args) {
		new Adressbuch();
	}

}
