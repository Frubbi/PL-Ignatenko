import os
import fnmatch
import tkinter as tk
from tkinter import filedialog, messagebox

def search_in_file(file_path, search_text):
    # Ищет текст в указанном файле и возвращает результаты
    results = []
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            for line_number, line in enumerate(file, start=1):
                if search_text in line:
                    results.append(f'Найдено в {file_path}, строка {line_number}: {line.strip()}')
    except (IOError, UnicodeDecodeError) as e:
        results.append(f'Не удалось прочитать файл {file_path}: {e}')
    return results

def search_in_directory(directory, search_text, file_pattern='*.txt'):
    # Обходит каталог и ищет текст в файлах
    results = []
    for dirpath, _, filenames in os.walk(directory):
        for filename in fnmatch.filter(filenames, file_pattern):
            file_path = os.path.join(dirpath, filename)
            results.extend(search_in_file(file_path, search_text))
    return results

def browse_directory():
    # Открывает диалог выбора каталога
    directory = filedialog.askdirectory()
    if directory:
        directory_entry.delete(0, tk.END)
        directory_entry.insert(0, directory)

def start_search():
    # Запускает поиск по тексту в файлах
    directory = directory_entry.get()
    search_text = search_entry.get()
    
    if not directory or not search_text:
        messagebox.showwarning("Внимание", "Пожалуйста, заполните все поля.")
        return
    
    results_text.delete(1.0, tk.END)  # Очистка предыдущих результатов
    results = search_in_directory(directory, search_text)
    
    if results:
        results_text.insert(tk.END, "\n".join(results))
    else:
        results_text.insert(tk.END, "Результаты не найдены.")

# Создание окна и интерфеса
window = tk.Tk()
window.title("Поиск текста в файлах")

tk.Label(window, text="Каталог:").grid(row=0, column=0, padx=5, pady=5)
directory_entry = tk.Entry(window, width=50)
directory_entry.grid(row=0, column=1, padx=5, pady=5)
tk.Button(window, text="Обзор", command=browse_directory).grid(row=0, column=2, padx=5, pady=5)

tk.Label(window, text="Текст для поиска:").grid(row=1, column=0, padx=5, pady=5)
search_entry = tk.Entry(window, width=50)
search_entry.grid(row=1, column=1, padx=5, pady=5)

tk.Button(window, text="Поиск", command=start_search).grid(row=2, column=1, padx=5, pady=10)

results_text = tk.Text(window, height=15, width=80)
results_text.grid(row=3, column=0, columnspan=3, padx=5, pady=5)

window.mainloop()