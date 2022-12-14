package main

import (
	"log"
	"os"

	"github.com/pocketbase/pocketbase"
	"github.com/pocketbase/pocketbase/apis"
	"github.com/pocketbase/pocketbase/core"
	"zihbot.eu/pfm/main/cmd"
	"zihbot.eu/pfm/main/transactions"
)

func main() {
	app := pocketbase.New()

	app.OnBeforeServe().Add(func(e *core.ServeEvent) error {
		e.Router.GET("/*", apis.StaticDirectoryHandler(os.DirFS("./pb_public"), true))
		return nil
	})

	app.RootCmd.AddCommand(cmd.NewSchemaCommand(app))

	transactions.Register(app)

	if err := app.Start(); err != nil {
		log.Fatal(err)
	}
}
