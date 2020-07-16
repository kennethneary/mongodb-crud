all: build

up:
	docker-compose --file docker/docker-compose.yml up

down:
	docker-compose --file docker/docker-compose.yml down

build:
	docker-compose --file docker/docker-compose.yml up --build
