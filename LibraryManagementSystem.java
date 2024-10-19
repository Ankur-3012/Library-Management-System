import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;




public class LibraryManagementSystem {
    private JFrame frame;
    private JTextField bookTitleField;
    private JTextField bookAuthorField;
    private DefaultListModel<Book> bookListModel;
    private JList<Book> bookList;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LibraryManagementSystem window = new LibraryManagementSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LibraryManagementSystem() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Library Management System");
        frame.setBounds(100, 100, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 245, 249));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102));
        headerPanel.setPreferredSize(new Dimension(800, 60));
        frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        ImageIcon logoIcon = new ImageIcon("OIP (1).jpeg");
        Image logoImage = logoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        headerPanel.add(logoLabel);

        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(240, 245, 249));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{100, 400, 150};
        contentPanel.setLayout(gbl_contentPanel);

        JLabel bookTitleLabel = new JLabel("Book Title:");
        bookTitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        GridBagConstraints gbc_bookTitleLabel = new GridBagConstraints();
        gbc_bookTitleLabel.anchor = GridBagConstraints.WEST;
        gbc_bookTitleLabel.insets = new Insets(10, 10, 10, 10);
        gbc_bookTitleLabel.gridx = 0;
        gbc_bookTitleLabel.gridy = 0;
        contentPanel.add(bookTitleLabel, gbc_bookTitleLabel);

        bookTitleField = new JTextField(20);
        bookTitleField.setFont(new Font("Arial", Font.PLAIN, 14));
        GridBagConstraints gbc_bookTitleField = new GridBagConstraints();
        gbc_bookTitleField.fill = GridBagConstraints.HORIZONTAL;
        gbc_bookTitleField.insets = new Insets(10, 10, 10, 10);
        gbc_bookTitleField.gridx = 1;
        gbc_bookTitleField.gridy = 0;
        contentPanel.add(bookTitleField, gbc_bookTitleField);

        JLabel bookAuthorLabel = new JLabel("Book Author:");
        bookAuthorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        GridBagConstraints gbc_bookAuthorLabel = new GridBagConstraints();
        gbc_bookAuthorLabel.anchor = GridBagConstraints.WEST;
        gbc_bookAuthorLabel.insets = new Insets(10, 10, 10, 10);
        gbc_bookAuthorLabel.gridx = 0;
        gbc_bookAuthorLabel.gridy = 1;
        contentPanel.add(bookAuthorLabel, gbc_bookAuthorLabel);

        bookAuthorField = new JTextField(20);
        bookAuthorField.setFont(new Font("Arial", Font.PLAIN, 14));
        GridBagConstraints gbc_bookAuthorField = new GridBagConstraints();
        gbc_bookAuthorField.fill = GridBagConstraints.HORIZONTAL;
        gbc_bookAuthorField.insets = new Insets(10, 10, 10, 10);
        gbc_bookAuthorField.gridx = 1;
        gbc_bookAuthorField.gridy = 1;
        contentPanel.add(bookAuthorField, gbc_bookAuthorField);

        JButton btnAddBook = new JButton("Add Book");
        btnAddBook.setFont(new Font("Arial", Font.BOLD, 14));
        btnAddBook.setBackground(new Color(0, 102, 204));
        btnAddBook.setForeground(Color.WHITE);
        btnAddBook.setPreferredSize(new Dimension(150, 40));
        btnAddBook.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        btnAddBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAddBook.setBackground(new Color(0, 153, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAddBook.setBackground(new Color(0, 102, 204));
            }
        });

        GridBagConstraints gbc_btnAddBook = new GridBagConstraints();
        gbc_btnAddBook.insets = new Insets(20, 0, 0, 0);
        gbc_btnAddBook.gridx = 1;
        gbc_btnAddBook.gridy = 2;
        contentPanel.add(btnAddBook, gbc_btnAddBook);

        btnAddBook.addActionListener(e -> addBook());

        bookListModel = new DefaultListModel<>();
        bookList = new JList<>(bookListModel);
        JScrollPane scrollPane = new JScrollPane(bookList);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 3;
        gbc_scrollPane.insets = new Insets(20, 10, 10, 10);
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 3;
        contentPanel.add(scrollPane, gbc_scrollPane);

        JButton btnDeleteBook = new JButton("Delete Book");
        btnDeleteBook.setFont(new Font("Arial", Font.BOLD, 14));
        btnDeleteBook.setBackground(new Color(255, 51, 51));
        btnDeleteBook.setForeground(Color.WHITE);
        btnDeleteBook.setPreferredSize(new Dimension(150, 40));
        btnDeleteBook.addActionListener(e -> deleteBook());

        GridBagConstraints gbc_btnDeleteBook = new GridBagConstraints();
        gbc_btnDeleteBook.insets = new Insets(20, 0, 0, 0);
        gbc_btnDeleteBook.gridx = 0;
        gbc_btnDeleteBook.gridy = 4;
        contentPanel.add(btnDeleteBook, gbc_btnDeleteBook);

        JButton btnBorrowBook = new JButton("Borrow Book");
        btnBorrowBook.setFont(new Font("Arial", Font.BOLD, 14));
        btnBorrowBook.setBackground(new Color(102, 204, 0));
        btnBorrowBook.setForeground(Color.WHITE);
        btnBorrowBook.setPreferredSize(new Dimension(150, 40));
        btnBorrowBook.addActionListener(e -> borrowBook());

        GridBagConstraints gbc_btnBorrowBook = new GridBagConstraints();
        gbc_btnBorrowBook.insets = new Insets(20, 0, 0, 0);
        gbc_btnBorrowBook.gridx = 1;
        gbc_btnBorrowBook.gridy = 4;
        contentPanel.add(btnBorrowBook, gbc_btnBorrowBook);

        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.setFont(new Font("Arial", Font.BOLD, 14));
        btnReturnBook.setBackground(new Color(0, 153, 255));
        btnReturnBook.setForeground(Color.WHITE);
        btnReturnBook.setPreferredSize(new Dimension(150, 40));
        btnReturnBook.addActionListener(e -> returnBook());

        GridBagConstraints gbc_btnReturnBook = new GridBagConstraints();
        gbc_btnReturnBook.insets = new Insets(20, 0, 0, 0);
        gbc_btnReturnBook.gridx = 2;
        gbc_btnReturnBook.gridy = 4;
        contentPanel.add(btnReturnBook, gbc_btnReturnBook);
    }

    private void addBook() {
        String title = bookTitleField.getText();
        String author = bookAuthorField.getText();
        if (!title.isEmpty() && !author.isEmpty()) {
            bookListModel.addElement(new Book(title, author));
            bookTitleField.setText("");
            bookAuthorField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter both title and author");
        }
    }

    private void deleteBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            bookListModel.removeElement(selectedBook);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a book to delete");
        }
    }

    private void borrowBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            if (!selectedBook.isBorrowed()) {
                selectedBook.borrowBook();
                bookList.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Book is already borrowed");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a book to borrow");
        }
    }

    private void returnBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            if (selectedBook.isBorrowed()) {
                selectedBook.returnBook();
                bookList.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Book is already available");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a book to return");
        }
    }
}
