# TECHDay Kubernetes demo

## Package the app:
`.\mvnw package`

## Run the app with Docker

## Build the app and DB using docker compose:
`docker compose build`

## Run the app and DB using docker compose:
`docker compose up`

Access http://localhost:8080

## Namespaces

Create the namespace:<br />
`kubectl create namespace techday`

Set current namespace:<br />
`kubectl config set-context --current --namespace=techday`


## Pods

Run the db in a pod: <br />
`kubectl run db --image=db:v1 --namespace=techday`

Get the the information about the pods: <br />
`kubectl get pods --namespace=techday`

Get detail information about the pod: <br />
`kubectl describe pod <pod-name>`

Get the pod logs: <br />
`kubectl logs <pod-name>`

See the pod definition: <br />
`kubectl get pod <pod-name> -o yaml`


## Deployments

Create the DB deploy: <br />
`kubectl create deployment db --image=db:v1 --port=5432`

Create the APP deploy with two replicas: <br />
`kubectl create deployment app --image=app:v1 --port=8080 --replicas=2`

Get the deploys in the current namespace: <br />
`kubectl get deployment`


## Secrets

Create secret:<br />

`kubectl create secret generic appSecret --from-literal=user=postgres`


## Services:

Create service for the DB:<br />
`kubectl expose deployment/db --port=5432`

Create service for the APP:<br />
`kubectl expose deployment/app --port=8080`


## Ingress:

Create ingress: <br />
`kubectl create ingress app --rule=/*=app:8080`

## Readyness and Liveness

Open the deployment yaml just created and add the following to the pod definition: 

```
livenessProbe:
    httpGet:
        path: /actuator/health
        port: 8080
        initialDelaySeconds: 5
        periodSeconds: 10
        failureThreshold: 3
readinessProbe:
    httpGet:
        path: /actuator/health
        port: 8080
        initialDelaySeconds: 5
        periodSeconds: 10
        failureThreshold: 3
```

## Rolling updates and Rollbacks

Build a new version of the app: <br />
`docker build -t app:v2 .`

Update the existing deploy to the new version: <br />
`kubectl set image deployments/app app=app:v2`

See the deploy status:  <br />
`kubectl rollout status deployments/app`

Undo the latest deploy to the previous version: <br />
`kubectl rollout undo deployments/app`


## Helm

Install/Upgrade helm chart:<br />
`helm upgrade --install <release name> --vales <values filee> <chart directory>`