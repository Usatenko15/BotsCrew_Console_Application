# BotsCrew University Console Application

The BotsCrew Console Application is a Java-based console application that allows users to interact with a university's department information.

## Features

The console application provides the following features:

- Displaying the head of a department
- Showing statistics for a department
- Calculating the average salary of a department
- Retrieving the employee count of a department

## Prerequisites

Before running the application, ensure that you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or higher
- Gradle

## Getting Started

To get started with the BotsCrew Console Application, follow these steps:

1. Clone the repository to your local machine:
2. Navigate to the project directory:
3. Build the project using Gradle:
4. Run the application:

## Database Configuration

The BotsCrew Console Application uses the H2 in-memory database for storing the university information. The database is automatically created and initialized with minimal set of test data when the application starts, and it is destroyed when the application stops. You don't need to manually configure or install any external database.

## Usage

Once the application is running, you can enter commands to interact with the console. Here are some example commands:

- `Who is head of department <department-name>` - Retrieves the head of the specified department.
- `Show <department-name> statistics` - Displays statistics for the specified department.
- `Calculate <department-name> average salary` - Calculates the average salary of the specified department.
- `Show <department-name> employee count` - Retrieves the employee count of the specified department.