import requests
import time 
import mysql.connector
from datetime import datetime
from datetime import date

cnx = mysql.connector.connect(
    user='root',
    password='root',
    host='localhost',
    database='cuotasgym'
)

cursor = cnx.cursor()

# Obtener todas las rutinas y el numero de telefono de cada cliente
cursor.execute("SELECT c.telefono, r.detalle FROM cliente c inner JOIN rutina r ON c.id = r.cliente_id;")

rows = cursor.fetchall()


def sendMessage(para, mensaje):
    url = 'http://localhost:3001/lead'
    
    data = {
        "message": mensaje,
        "phone": para
    }
    headers = {
        'Content-Type':'application/json'
    }
    print(data)
    response = requests.post(url, json=data, headers=headers)
    time.sleep(10)
    return response

for row in rows:

        # Enviar mensaje
        sendMessage(row[0], row[1])


cursor.close()
cnx.close()

