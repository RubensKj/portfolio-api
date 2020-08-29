#!/bin/bash
default_folder="/opt/$(SERVICE_NAME)"

if ! [ -s "$default_folder/application.yml" ]
then 
    echo "File application.yml does not exists. Moving from artifact folder."
    mv ./$(ARTIFACT_NAME)/$(SERVICE_NAME)/application.yml $default_folder/.
fi
