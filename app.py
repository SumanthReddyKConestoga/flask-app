from flask import Flask, request, jsonify
import os

app = Flask(__name__)


# Example endpoint
@app.route("/")
def hello():
    return "Hello, Dockerized Flask!"


# Example: write to persistent volume
@app.route("/write", methods=["POST"])
def write_file():
    data = request.json or {}
    os.makedirs("/app/data", exist_ok=True)
    with open("/app/data/output.txt", "a") as f:
        f.write(data.get("text", "") + "\n")
    return jsonify(status="saved"), 201


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
