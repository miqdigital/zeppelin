/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { NzCardModule } from 'ng-zorro-antd/card';

import { ShareModule } from '@zeppelin/share';
import { NzIconModule } from 'ng-zorro-antd/icon';

import { NotebookSearchRoutingModule } from './notebook-search-routing.module';
import { NotebookSearchComponent } from './notebook-search.component';
import { NotebookSearchResultItemComponent } from './result-item/result-item.component';

@NgModule({
  declarations: [NotebookSearchComponent, NotebookSearchResultItemComponent],
  imports: [CommonModule, NotebookSearchRoutingModule, ShareModule, NzCardModule, FormsModule, NzIconModule]
})
export class NotebookSearchModule {}
