#!/bin/bash
default_folder="/opt/$(SERVICE_NAME)"

echo "Validating if application.yml in the environment."
if ! [ -s "$default_folder/application.yml" ]
then 
    echo "File application.yml does not exists. Moving from artifact folder."
    mv ./$(ARTIFACT_NAME)/$(ARTIFACT_NAME)/$(SERVICE_NAME)/application.yml $default_folder/.
else 
    echo "File application.yml already exists, but moving from artifact folder, in case of new properties."
    mv ./$(ARTIFACT_NAME)/$(ARTIFACT_NAME)/$(SERVICE_NAME)/application.yml $default_folder/.
fi

echo "Validating if start.sh exists."
if ! [ -s "$default_folder/start.sh" ]
then
    echo "File start.sh was not found. Moving from artifact folder."
    mv ./$(ARTIFACT_NAME)/$(ARTIFACT_NAME)/$(SERVICE_NAME)/start.sh $default_folder/.
fi