#!/bin/bash
service="/etc/systemd/system/$(SERVICE_NAME).service"

echo "Executing the service. Service: $service"
if  [ -f "$service" ]
then
    echo "Executing a daemon-reload"
    systemctl daemon-reload

    echo "Stopping the service.."
    systemctl stop $(SERVICE_NAME).service

    echo "Starting the service with new updated things."
    systemctl start $(SERVICE_NAME).service
else 
    echo "$(SERVICE_NAME) service does not exists. Cannot be started.."
fi