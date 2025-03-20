FROM debian:12-slim AS build

# setup debian env
RUN apt-get update && \
    apt-get install -y wget gcc libz-dev && \
    rm -rf /var/lib/apt/lists/*

# install graalvm manually
RUN wget https://github.com/graalvm/graalvm-ce-builds/releases/download/jdk-21.0.2/graalvm-community-jdk-21.0.2_linux-x64_bin.tar.gz && \
    tar -xvzf graalvm-community-jdk-21.0.2_linux-x64_bin.tar.gz && \
    rm graalvm-community-jdk-21.0.2_linux-x64_bin.tar.gz

# Set the GraalVM installation path
ENV GRAALVM_HOME=/graalvm-community-openjdk-21.0.2+13.1
ENV PATH=$GRAALVM_HOME/bin:$PATH

# set working directory
WORKDIR /app

# copy project files
COPY . .

# compile native image
RUN ./gradlew nativeCompile --no-daemon

# minimal but comaptable ~5mb
FROM frolvlad/alpine-glibc:alpine-3.20

# Set up workdir
WORKDIR /app

# Copy the native executable from the build stage
COPY --from=build /app/build/native/nativeCompile/portfolio /app/portfolio

# Set executable permissions
RUN chmod +x /app/portfolio

# Expose application port
EXPOSE 3000

# Start the application
ENTRYPOINT ["/app/portfolio"]