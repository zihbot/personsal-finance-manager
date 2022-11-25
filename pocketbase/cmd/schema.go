package cmd

import (
	"encoding/json"
	"log"
	"os"

	"github.com/pocketbase/pocketbase/core"
	"github.com/pocketbase/pocketbase/models"
	"github.com/spf13/cobra"
)

func NewSchemaCommand(app core.App) *cobra.Command {
	command := &cobra.Command{
		Use:   "schema",
		Short: "Upgrades your schema",
		Long:  "Upgrades your schema with the pb_schema.json in the current directory",
		Run: func(command *cobra.Command, args []string) {
			var jsonData []byte
			jsonData, err := os.ReadFile("./pb_schema.json")
			if err != nil {
				log.Println(err)
				return
			}

			collections := []*models.Collection{}
			if err := json.Unmarshal(jsonData, &collections); err != nil {
				log.Println(err)
				return
			}

			if err := app.Dao().ImportCollections(collections, true, nil); err != nil {
				log.Println(err)
				return
			}
		},
	}

	return command
}
