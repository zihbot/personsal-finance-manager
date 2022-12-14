package transactions

import (
	"github.com/pocketbase/pocketbase"
	"github.com/pocketbase/pocketbase/core"
)

func Register(app *pocketbase.PocketBase) {
	app.OnRecordAfterCreateRequest().Add(AccountAfterCreate)
}

func AccountAfterCreate(e *core.RecordCreateEvent) error {
	if e.Record.Collection().Name == "transactions" {
		sourceId := e.Record.GetString("source")
		targetId := e.Record.GetString("target")
		amount := e.Record.GetInt("value")

		if sourceId != "" {
			updateAccount(sourceId, -amount)
		}

		if targetId != "" {
			updateAccount(targetId, -amount)
		}
	}

	return nil
}

func updateAccount(accountId string, amount int) {

}
