
# EcoClean App

## Overview
EcoClean is an Android application that allows users to request garbage pickup services. The app collects the user's location coordinates and stores the request in Firebase Firestore, enabling admins to view and act on pickup requests.

## Features
- Request garbage pickup
- Fetch real-time user location
- Store data in Firebase Firestore
- Simple and clean UI

## Technologies Used
- Java
- XML
- Firebase Firestore
- Google Location Services

## Project Structure
- EcoCleanActivity.java → Handles location and Firestore logic
- activity_ecoclean.xml → UI layout for request screen

## How It Works
- User enters address
- Fetches current GPS location
- Clicks "Request Pickup"
- Data (address + coordinates) is stored in Firestore
- Admin can view and act on requests

## Future Improvements
- Admin dashboard with map view
- Real-time tracking of pickup agent
- Push notifications
- User authentication

## Author
Lakshay Jain
