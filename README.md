# Software Engineering - Web Application: Parfemologija

Web Application that serves as an online local fragrance selling & information system. Developed with AngularJS & Java Spring boot. Key Points:
- MVC design architecture used on the frontend with TailwindCSS for styling
- Layered design architecture used on the backend (DAO, Serice, Rest)
- Monorepo structure for the entire project
- Online Postrgres database via Supabase

### Running Deployed:
The application is depployed as of 15th June 2025 on https://parfemologija.onrender.com

### Running Local:
To run the application localy, the backend and frontend need to be individually hosted on ports 4200 for the FE and 8081 for the BE. For the database, the online deployed database is on Supabase, however, for running locally the database DDL is present within the repository and can be used to run a local copy of the application.


# Versions Rundown

## Version 1.0 - Functions (Mostly Backend)
For this version we focused on creating the Backend carefully and preparing all the individual parts and components we will need for development later.
- Backend working API CRUD for Fragrances, Articles, Users, Brands...
- Authentication and Authorisation for the frontend
- Initial page structure and navigation for the frontend
- Four main pages to be implemented: Homepage, Browse Fragrances, Browse articles & Profile

## Version 1.1 - Full App
For this version, a working application is developed, including functions like:
- Browsing, Viewing and Ading Fragrances
- Browsing, Viewing and Ading Articles
- Wishlist & Collection Items...
