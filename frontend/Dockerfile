# Use a Node.js base image
FROM node:20.11.1-alpine3.19 as prod-builder

# Set the working directory in the container
WORKDIR /app
RUN npm install -g vite
# Copy package.json and package-lock.json to the working directory
COPY . .

# Install dependencies
RUN npm install --force

# Copy all files from the current directory to the working directory in the container

# Build the frontend application

# Expose port 80 to the outside world
EXPOSE 5173

# Run the frontend application when the container starts
CMD ["npm", "run", "dev"]
