# Purchase & Sale Announcements 

## Deployment

To deploy PASA app just install docker aplication and setup docker-compose.

1. [Install docker](https://https://www.docker.com/get-started "Install page")

2. Run docker application.

3. Open terminal and download repository, **remember** about recursive frontend branch!

```bash
git clone --single-branch --branch spring_data_mvc_deployment --recurse-submodules -j8 git://github.com/creativecbr/spring_framework.git
```
4. Get into folder named
```bash
spring_framework
```

5. Write 
```bash
docker-compose up --build
``` 
and wait about 5 seconds.

6. Open your favourite internet browser and go to:

```bash
http://localhost:8084/index.html
```

7. Buy, sell, HAVE FUN!

## Author
Leśniewski Paweł © 2020, *student No.175724*
