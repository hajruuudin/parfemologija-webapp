# Stage 1: Build the Angular application
FROM node:20-alpine AS builder
WORKDIR /app

# Copy package.json and package-lock.json (or yarn.lock if you use Yarn)
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the Angular application for production
RUN npm run build

# Stage 2: Serve the application using Node.js (for SSR)
FROM node:20-alpine AS server
WORKDIR /app

# Copy server dependencies (only production dependencies)
COPY --from=builder /app/package*.json ./
RUN npm install --omit=dev

# Copy the built application and server files
COPY --from=builder /app/dist/parfemologija-fe/browser ./browser
COPY --from=builder /app/dist/parfemologija-fe/server ./server

# Copy any static assets from the public folder
COPY --from=builder /app/public ./browser/public

# Expose the port your application will run on
EXPOSE 4000

# Command to start the server
CMD ["node", "server/server.mjs"]