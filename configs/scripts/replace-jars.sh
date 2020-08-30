#!/bin/bash
default_folder="/opt/$(SERVICE_NAME)"

if ! [ -f "$default_folder/$(JAR_NAME).jar" ]
then 
    echo "$(JAR_NAME).jar does not exists. Moving from artifact folder."
    mv ./$(ARTIFACT_NAME)/$(SERVICE_NAME)/$(JAR_NAME).jar $default_folder/.
else
    echo "$(JAR_NAME).jar already exists, creating a backup."
    mkdir "$default_folder/$(SERVICE_NAME)_backup"
    mv "$default_folder/$(JAR_NAME).jar" "$default_folder/$(SERVICE_NAME)_backup/."

    echo "Moving updated jar to application folder.."
    mv ./$(ARTIFACT_NAME)/$(SERVICE_NAME)/$(JAR_NAME).jar $default_folder/.
fi

echo "Adding permission to $(JAR_NAME).jar"
chmod +x $default_folder/$(JAR_NAME).jar
