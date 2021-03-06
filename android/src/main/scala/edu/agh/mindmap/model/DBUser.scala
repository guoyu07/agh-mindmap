/*
 * Copyright 2013 Katarzyna Szawan <kat.szwn@gmail.com>
 *     and Michał Rus <https://michalrus.com/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.agh.mindmap.model

import edu.agh.mindmap.util.{ExplicitNull, DBHelper}
import android.database.sqlite.SQLiteDatabase

trait DBUser {

  private var _db: Option[DBHelper] = None
  def setDb(dbHelper: DBHelper) = _db = Some(dbHelper)
  def dbr = {
    require(_db.isDefined) // safe to throw here, crucial functionality
    _db.map(_.getReadableDatabase).fold { (throw new Exception): SQLiteDatabase } { x => x }
  }
  def dbw = {
    require(_db.isDefined) // safe to throw here, crucial functionality
    _db.map(_.getWritableDatabase).fold { (throw new Exception): SQLiteDatabase } { x => x }
  }

}
