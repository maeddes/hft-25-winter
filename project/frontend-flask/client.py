from flask import Flask, render_template_string, request
import requests

app = Flask(__name__)

API_BASE = "http://localhost:8080/items"

TEMPLATE = """
<!DOCTYPE html>
<html>
<head>
    <title>Item API Client (Flask)</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 2rem; }
        input, button { padding: 0.5rem; margin: 0.25rem; }
        pre { background: #f4f4f4; padding: 1rem; }
    </style>
</head>
<body>

<h2>Item API Client (via Flask)</h2>

<form method="post" action="/post">
    <h3>POST: Create/Add</h3>
    <input name="name" placeholder="Name">
    <input name="quantity" type="number" placeholder="Quantity">
    <button type="submit">POST</button>
</form>

<form method="post" action="/put">
    <h3>PUT: Replace</h3>
    <input name="name" placeholder="Name">
    <input name="quantity" type="number" placeholder="Quantity">
    <button type="submit">PUT</button>
</form>

<form method="post" action="/get">
    <h3>GET: Single Item</h3>
    <input name="name" placeholder="Name">
    <button type="submit">GET</button>
</form>

<form method="post" action="/delete">
    <h3>DELETE: Item</h3>
    <input name="name" placeholder="Name">
    <button type="submit">DELETE</button>
</form>

<form method="post" action="/all">
    <h3>GET ALL</h3>
    <button type="submit">GET ALL</button>
</form>

<h3>Response</h3>
<pre>{{ response }}</pre>

</body>
</html>
"""

def fmt(response):
    """Format JSON or plaintext response for display."""
    try:
        return response.json()
    except:
        return response.text

@app.route("/", methods=["GET"])
def index():
    return render_template_string(TEMPLATE, response="")

@app.route("/post", methods=["POST"])
def post_item():
    name = request.form["name"]
    qty = int(request.form["quantity"])
    r = requests.post(API_BASE, json={"name": name, "quantity": qty})
    return render_template_string(TEMPLATE, response=fmt(r))

@app.route("/put", methods=["POST"])
def put_item():
    name = request.form["name"]
    qty = int(request.form["quantity"])
    r = requests.put(f"{API_BASE}/{name}", json={"name": name, "quantity": qty})
    return render_template_string(TEMPLATE, response=fmt(r))

@app.route("/get", methods=["POST"])
def get_item():
    name = request.form["name"]
    r = requests.get(f"{API_BASE}/{name}")
    return render_template_string(TEMPLATE, response=fmt(r))

@app.route("/delete", methods=["POST"])
def delete_item():
    name = request.form["name"]
    r = requests.delete(f"{API_BASE}/{name}")
    return render_template_string(TEMPLATE, response=r.status_code)

@app.route("/all", methods=["POST"])
def get_all():
    r = requests.get(API_BASE)
    return render_template_string(TEMPLATE, response=fmt(r))

if __name__ == "__main__":
    app.run(port=5123, debug=True)
