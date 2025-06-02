import subprocess
import os
import json

HISTORY_FILE = "/storage/emulated/0/Download/spotdl_history.json"

def save_to_history(query):
    history = []
    if os.path.exists(HISTORY_FILE):
        with open(HISTORY_FILE, "r") as f:
            history = json.load(f)

    history.append(query)

    with open(HISTORY_FILE, "w") as f:
        json.dump(history, f)

def get_history():
    if os.path.exists(HISTORY_FILE):
        with open(HISTORY_FILE, "r") as f:
            return json.load(f)
    return []

def download_music(query):
    try:
        output_dir = "/storage/emulated/0/Download/"
        subprocess.run(["spotdl", query, "--output", output_dir], check=True)
        save_to_history(query)
        return "Download completato!"
    except Exception as e:
        return f"Errore: {str(e)}"
