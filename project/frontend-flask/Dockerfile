FROM python:3.12-slim
WORKDIR /app
COPY client.py ./client.py
RUN pip install --no-cache-dir flask requests
EXPOSE 5123
ENTRYPOINT ["python", "client.py"]
