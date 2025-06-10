# Use a specific, slim Python base
FROM python:3.10-slim

# Set working dir
WORKDIR /app

# Copy and install dependencies in one layer
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy your Flask app code
COPY app.py .

# Create and switch to non-root user
RUN useradd --create-home appuser
USER appuser

# Env var and exposed port
ENV FLASK_ENV=production
EXPOSE 5000

# Command to run the app
ENTRYPOINT ["python", "app.py"]
