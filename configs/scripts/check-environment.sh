#!/bin/bash
service="/etc/systemd/system/$(SERVICE_NAME).service"
default_folder="/opt"

echo "Validating if service exists in the system. Service: $service"
if ! [ -f "$service" ]
then
    echo "$(SERVICE_NAME) does not exists. Moving service from artifact folder."
    mv ./$(ARTIFACT_NAME)/$(ARTIFACT_NAME)/$(SERVICE_NAME)/$(SERVICE_NAME).service /etc/systemd/system/.
fi

echo "Validating default folder of $default_folder/$(SERVICE_NAME)"
if ! [ -d "$default_folder/$(SERVICE_NAME)" ]
then
    echo "$default_folder/$(SERVICE_NAME) does not exists. Creating the default folder"
    mkdir $default_folder/$(SERVICE_NAME)
fi