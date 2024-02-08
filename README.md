# TECHDay Kubernetes demo

## Package the app:
`.\mvnw package`

# Run the app with Docker

## Build the app and DB using docker compose:
`docker compose build`

## Run the app and DB using docker compose:
`docker compose up`

Access http://localhost:8080


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