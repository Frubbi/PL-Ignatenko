import requests
import json
import tkinter as tk
from tkinter import messagebox

def fetch_repo_info():
    repo_name = entry.get()
    url = f"https://api.github.com/repos/{repo_name}"
    
    try:
        response = requests.get(url)
        response.raise_for_status()  
        repo_info = response.json()
        
        result = {
            'company': repo_info.get('owner', {}).get('company'),
            'created_at': repo_info.get('created_at'),
            'email': repo_info.get('owner', {}).get('email'),
            'id': repo_info.get('id'),
            'name': repo_info.get('name'),
            'url': repo_info.get('owner', {}).get('url'),
        }
        
        with open('repo_info.json', 'w') as json_file:
            json.dump(result, json_file, indent=4)
        
        messagebox.showinfo("Success", "Информация успешно сохранена в repo_info.json")
    
    except requests.exceptions.HTTPError as http_err:
        messagebox.showerror("Error", f"HTTP error occurred: {http_err}")
    except Exception as err:
        messagebox.showerror("Error", f"An error occurred: {err}")

root = tk.Tk()
root.title("GitHub Repo Info Fetcher")

label = tk.Label(root, text="Введите имя репозитория (например, 'kubernetes/kubernetes'):")
label.pack()

entry = tk.Entry(root, width=50)
entry.pack()

button = tk.Button(root, text="Получить информацию", command=fetch_repo_info)
button.pack()

root.mainloop()