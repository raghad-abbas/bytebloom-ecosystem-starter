
This repository contains our team’s implementation of the ByteBloom Ecosystem project using Kotlin.
The project reads data from CSV files (mentees.csv, teams.csv, performance.csv) and converts them into clean models to generate simple reports.

Project Structure
	•	Main.kt – Entry point
	•	parsers/ – CSV parsing functions
	•	data/ – Raw data classes
	•	models/ – Processed models
	•	resources/ – CSV files

▶ How to Run the Project
	1.	Open the project in IntelliJ IDEA
	2.	Make sure Gradle syncs automatically
	3.	Navigate to:

src/main/kotlin/Main.kt

	4.	Click Run

The CLI will start and allow you to view reports and data insights.

Team Members
	•	Raghad Abbas 
  • Alaa Husam
  • Soad Alastal
  • Shahea Hassan 

  🏗 Project Structure

project-root/
│
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── Main.kt               → Application entry point
│   │   │   ├── data/                 → Raw models (CSV structures)
│   │   │   ├── parsers/              → CSV parsing logic
│   │   │   ├── models/               → Clean processed models
│   │   └── resources/
│   │       ├── mentees.csv
│   │       ├── teams.csv
│   │       ├── performance.csv
│   │
│   └── test/                         → Unit tests
│
├── build.gradle.kts                  → Project dependencies & config
├── README.md                         → Project documentation
└── .gitignore


  • Shahed Musallm
  
