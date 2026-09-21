from flask import Flask, request, jsonify

app = Flask(__name__)


@app.route("/validate", methods=["POST"])
def validate_patient():

    data = request.get_json()

    name = data.get("name")
    age = data.get("age")
    temperature = data.get("temperature")
    heart_rate = data.get("heartRate")
    systolic = data.get("systolicBP")
    diastolic = data.get("diastolicBP")

    issues = []

    # Basic validation
    if not name:
        issues.append("Patient name is required")

    if age is None or age <= 0 or age > 120:
        issues.append("Invalid age")

    # Vital validation
    if temperature is None or temperature < 90 or temperature > 110:
        issues.append("Temperature is outside the expected range")

    if heart_rate is None or heart_rate < 40 or heart_rate > 180:
        issues.append("Heart rate is outside the expected range")

    if systolic is None or systolic < 70 or systolic > 200:
        issues.append("Systolic BP is outside the expected range")

    if diastolic is None or diastolic < 40 or diastolic > 120:
        issues.append("Diastolic BP is outside the expected range")

    # Check blood pressure relationship
    if systolic is not None and diastolic is not None:
        if systolic <= diastolic:
            issues.append("Systolic BP should be greater than diastolic BP")

    if len(issues) == 0:

        return jsonify({
            "valid": True,
            "anomaly": False,
            "message": "Patient data is valid",
            "issues": []
        })

    else:

        return jsonify({
            "valid": False,
            "anomaly": True,
            "message": "Abnormal or inconsistent patient data detected",
            "issues": issues
        })


@app.route("/", methods=["GET"])
def home():

    return jsonify({
        "message": "Patient Validation Microservice is running"
    })


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)