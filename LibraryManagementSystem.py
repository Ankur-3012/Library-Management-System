import tkinter as tk
from tkinter import messagebox, Listbox, Scrollbar
from PIL import Image, ImageTk  

class Book:
    def __init__(self, title, author):
        self.title = title
        self.author = author
        self.is_borrowed = False

    def __str__(self):
        status = " (Borrowed)" if self.is_borrowed else " (Available)"
        return f"{self.title} by {self.author}{status}"

class LibraryManagementSystem:
    def __init__(self, root):
        self.root = root
        self.root.title("Library Management System")
        self.root.geometry("800x600")

        self.load_logo()

        self.books = []  

        self.frame = tk.Frame(self.root, bg="#003366")
        self.frame.pack(fill=tk.BOTH, expand=True, padx=20, pady=20)

        self.title_label = tk.Label(self.frame, text="Library Management System", font=("Helvetica", 24), bg="#003366", fg="white")
        self.title_label.pack(pady=(60, 10))

        self.book_listbox_frame = tk.Frame(self.frame)
        self.book_listbox_frame.pack(fill=tk.BOTH, expand=True)

        self.book_listbox = Listbox(self.book_listbox_frame, font=("Arial", 12))
        self.book_listbox.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)

        self.scrollbar = Scrollbar(self.book_listbox_frame, command=self.book_listbox.yview)
        self.scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        self.book_listbox.config(yscrollcommand=self.scrollbar.set)

        self.entry_frame = tk.Frame(self.frame, bg="#F0F5F9")
        self.entry_frame.pack(pady=10)

        self.title_entry = tk.Entry(self.entry_frame, width=30, font=("Arial", 12))
        self.title_entry.grid(row=0, column=1, padx=10, pady=5)
        tk.Label(self.entry_frame, text="Book Title:", bg="#F0F5F9", font=("Arial", 12)).grid(row=0, column=0)

        self.author_entry = tk.Entry(self.entry_frame, width=30, font=("Arial", 12))
        self.author_entry.grid(row=1, column=1, padx=10, pady=5)
        tk.Label(self.entry_frame, text="Book Author:", bg="#F0F5F9", font=("Arial", 12)).grid(row=1, column=0)

        self.add_button = tk.Button(self.frame, text="Add Book", command=self.add_book, bg="#0066CC", fg="white", font=("Arial", 12))
        self.add_button.pack(fill=tk.X, pady=5)

        self.delete_button = tk.Button(self.frame, text="Delete Book", command=self.delete_book, bg="#FF3333", fg="white", font=("Arial", 12))
        self.delete_button.pack(fill=tk.X, pady=5)

        self.borrow_button = tk.Button(self.frame, text="Borrow Book", command=self.borrow_book, bg="#66CC00", fg="white", font=("Arial", 12))
        self.borrow_button.pack(fill=tk.X, pady=5)

        self.return_button = tk.Button(self.frame, text="Return Book", command=self.return_book, bg="#0099FF", fg="white", font=("Arial", 12))
        self.return_button.pack(fill=tk.X, pady=5)

    def load_logo(self):
        logo_img = Image.open("OIP (1).jpeg") 
        logo_img = logo_img.resize((50, 50))  
        self.logo_photo = ImageTk.PhotoImage(logo_img)

        self.header_frame = tk.Frame(self.root, bg="#F0F5F9")
        self.header_frame.pack(fill=tk.X, pady=10)

        logo_label = tk.Label(self.header_frame, image=self.logo_photo, bg="#F0F5F9")
        logo_label.image = self.logo_photo
        logo_label.pack(side=tk.LEFT, padx=10)

    def add_book(self):
        title = self.title_entry.get().strip()
        author = self.author_entry.get().strip()
        if title and author:
            book = Book(title, author)
            self.books.append(book)
            self.update_book_list()
            self.title_entry.delete(0, tk.END)
            self.author_entry.delete(0, tk.END)
        else:
            messagebox.showwarning("Input Error", "Please enter both title and author.")

    def delete_book(self):
        try:
            selected_index = self.book_listbox.curselection()[0]
            del self.books[selected_index]
            self.update_book_list()
        except IndexError:
            messagebox.showwarning("Selection Error", "Please select a book to delete.")

    def borrow_book(self):
        try:
            selected_index = self.book_listbox.curselection()[0]
            if not self.books[selected_index].is_borrowed:
                self.books[selected_index].is_borrowed = True
                self.update_book_list()
            else:
                messagebox.showinfo("Borrow Error", "Book is already borrowed.")
        except IndexError:
            messagebox.showwarning("Selection Error", "Please select a book to borrow.")

    def return_book(self):
        try:
            selected_index = self.book_listbox.curselection()[0]
            if self.books[selected_index].is_borrowed:
                self.books[selected_index].is_borrowed = False
                self.update_book_list()
            else:
                messagebox.showinfo("Return Error", "Book is already available.")
        except IndexError:
            messagebox.showwarning("Selection Error", "Please select a book to return.")

    def update_book_list(self):
        self.book_listbox.delete(0, tk.END)
        for book in self.books:
            self.book_listbox.insert(tk.END, str(book))

if __name__ == "__main__":
    root = tk.Tk()
    LibraryManagementSystem(root)
    root.mainloop()
